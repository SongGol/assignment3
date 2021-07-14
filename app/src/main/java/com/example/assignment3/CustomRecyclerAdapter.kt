package com.example.assignment3

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.databinding.RecyclerviewItemBinding

class CustomRecyclerAdapter(var dataSet: ArrayList<Stock>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){
    private lateinit var binding: RecyclerviewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerAdapter.ViewHolder {
        binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomRecyclerAdapter.ViewHolder, position: Int) {
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

    class ViewHolder(private val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Stock) {
            binding.recyclerItemName.text = data.name
            binding.recyclerItemPrice.text = data.currentValue.toString()
            binding.recyclerItemVolume.text = data.volume.toString()
            //화살표는 등락에 따라 결정
            binding.recyclerItemTriangle.text = "▼"
            binding.recyclerItemDif.text = (data.startValue - data.currentValue).toString()
            //전날 종가는 시작가와 같다고 하자.
            binding.recyclerItemDifPer.text = "00.00%"
        }
    }
}