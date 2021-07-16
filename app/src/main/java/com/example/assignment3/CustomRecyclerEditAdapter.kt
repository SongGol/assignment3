package com.example.assignment3

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.databinding.RecyclerviewEditItemBinding

class CustomRecyclerEditAdapter(var dataSet: ArrayList<Stock>, private val listener: ItemDragListener) : RecyclerView.Adapter<CustomRecyclerEditAdapter.ViewHolder>(), ItemActionListener{
    private lateinit var binding: RecyclerviewEditItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerEditAdapter.ViewHolder {
        binding = RecyclerviewEditItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CustomRecyclerEditAdapter.ViewHolder, position: Int) {
        val item: Stock = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataSet.size

    class ViewHolder(private val binding: RecyclerviewEditItemBinding, listener: ItemDragListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.editDragIv.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }
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

    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) return
        val fromItem = dataSet.removeAt(from)
        dataSet.add(to, fromItem)
        notifyItemMoved(from, to)
    }
}