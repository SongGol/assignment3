package com.example.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.databinding.RecyclerviewEditItemBinding

class CustomRecyclerEditAdapter(var dataSet: ArrayList<Stock>) : RecyclerView.Adapter<CustomRecyclerEditAdapter.ViewHolder>(){
    private lateinit var binding: RecyclerviewEditItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerEditAdapter.ViewHolder {
        binding = RecyclerviewEditItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomRecyclerEditAdapter.ViewHolder, position: Int) {
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

    class ViewHolder(private val binding: RecyclerviewEditItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Stock) {
            binding.name.text = data.name
            binding.price.text = data.currentValue.toString()
            binding.volume.text = data.volume.toString()
            //화살표는 등락에 따라 결정
            binding.triangle.text = "▼"
            binding.dif.text = (data.startValue - data.currentValue).toString()
            //전날 종가는 시작가와 같다고 하자.
            binding.difPer.text = "00.00%"
        }
    }
}