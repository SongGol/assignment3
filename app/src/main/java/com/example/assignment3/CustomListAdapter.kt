package com.example.assignment3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.assignment3.databinding.InterestListviewItemBinding

class CustomListAdapter(context: Context, private val dataList: ArrayList<Stock>) : BaseAdapter(){
    private lateinit var binding: InterestListviewItemBinding
    private val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = InterestListviewItemBinding.inflate(inflator, parent, false)

        binding.listItemNameTv.text = dataList[position].name
        binding.listItemCodeTv.text = dataList[position].code
        binding.listItemMarketTv.text = dataList[position].marketName
        binding.listItemCheckCb.isChecked = dataList[position].check
        if (dataList[position].country == "korea") {
            binding.listItemCountryIv.setImageResource(R.drawable.korea)
        } else {
            binding.listItemCountryIv.setImageResource(R.drawable.us)
        }

        return binding.root
    }

}