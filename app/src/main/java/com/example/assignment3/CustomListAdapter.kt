package com.example.assignment3

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.example.assignment3.databinding.InterestListviewItemBinding

class CustomListAdapter(context: Context, private val dataList: ArrayList<Stock>) : BaseAdapter(), Filterable{
    private lateinit var binding: InterestListviewItemBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var filteredItemList = dataList
    private val listFilter: Filter? = null

    override fun getCount(): Int {
        return filteredItemList.size
    }

    override fun getItem(position: Int): Any {
        return filteredItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = InterestListviewItemBinding.inflate(inflater, parent, false)

        binding.listItemNameTv.text = filteredItemList[position].name
        binding.listItemCodeTv.text = filteredItemList[position].code
        binding.listItemMarketTv.text = filteredItemList[position].marketName
        binding.listItemCheckCb.isChecked = filteredItemList[position].check
        if (filteredItemList[position].country == "korea") {
            binding.listItemCountryIv.setImageResource(R.drawable.korea)
        } else {
            binding.listItemCountryIv.setImageResource(R.drawable.us)
        }

        return binding.root
    }

    override fun getFilter(): Filter = listFilter?:ListFilter()

    inner class ListFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()

            if (constraint == null || constraint.isEmpty()) {
                result.values = dataList
                result.count = dataList.size
            } else {
                val itemList = ArrayList<Stock>()
                for (item in dataList) {
                    if (item.name.uppercase().contains(constraint.toString().uppercase())) {
                        itemList.add(item)
                    }
                }
                result.values = itemList
                result.count = itemList.size
            }

            return result
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredItemList = results?.values as ArrayList<Stock>

            if (results.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }

    }

}