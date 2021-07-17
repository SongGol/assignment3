package com.example.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.databinding.RecyclerviewGridItemBinding

class CustomRecyclerGridAdapter(var dataSet: ArrayList<Stock>): RecyclerView.Adapter<CustomRecyclerGridAdapter.ViewHolder>(){
    private lateinit var binding: RecyclerviewGridItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerGridAdapter.ViewHolder {
        binding = RecyclerviewGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomRecyclerGridAdapter.ViewHolder, position: Int) {
        val item: Stock = dataSet[position]
        holder.bind(item)
/*
        //리스너 등록
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, NoteActivity::class.java)
            //intent에 Parcelable객체 값 넣어 전달
            val data = RecyclerItem(item.id, item.title, item.content, item.modified)
            intent.putExtra("data", data)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }

 */
    }

    override fun getItemCount(): Int = dataSet.size

    class ViewHolder(private val binding: RecyclerviewGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Stock) {
            //종목정보
            binding.recyclerGridItemStatus.setImageResource(if(data.status == 0) 0 else if (data.status == 1) R.drawable.management else R.drawable.warning)
            //종목명
            binding.recyclerGridItemName.text = data.name
            //색상 설정
            val color = if (data.currentValue <  data.startValue) ContextCompat.getColor(binding.root.context, R.color.medium_blue)  else ContextCompat.getColor(binding.root.context, R.color.dark_red)
            binding.recyclerGridItemPrice.setTextColor(color)
            binding.recyclerGridItemDif.setTextColor(color)
            binding.recyclerGridItemDifPer.setTextColor(color)
            binding.recyclerGridItemTriangle.setTextColor(color)
            //삼각형 모양설정
            binding.recyclerGridItemTriangle.text = if (data.currentValue < data.startValue) "▼" else "▲"
            //percent 설정
            binding.recyclerGridItemDifPer.text = makePercent((data.currentValue - data.startValue).toDouble() / data.startValue.toDouble() * 100.0)
            //숫자 설정
            if (data.country == "korea") {
                binding.recyclerGridItemDif.text = makeDecimal(data.currentValue - data.startValue)
                binding.recyclerGridItemPrice.text = makeDecimal(data.currentValue)
            } else if (data.country == "us"){
                binding.recyclerGridItemDif.text = makeUsPrice((data.currentValue - data.startValue).toDouble()/100)
                binding.recyclerGridItemPrice.text = makeUsPrice(data.currentValue.toDouble()/100)
            }
            binding.recyclerGridItemVolume.text = makeDecimal(data.volume)
        }
    }
}