package com.example.assignment3

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.databinding.RecyclerviewEditItemBinding

class CustomRecyclerEditAdapter(var dataSet: ArrayList<Stock>, private val listener: ItemDragListener) : RecyclerView.Adapter<CustomRecyclerEditAdapter.ViewHolder>(), ItemActionListener{
    private lateinit var binding: RecyclerviewEditItemBinding
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerEditAdapter.ViewHolder {
        binding = RecyclerviewEditItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CustomRecyclerEditAdapter.ViewHolder, position: Int) {
        val item: Stock = dataSet[position]
        holder.bind(item)
/*
        holder.itemView.setOnClickListener {
            dataSet[position].check = !item.check
            binding.checkCb.isChecked = dataSet[position].check
            notifyDataSetChanged()
        }*/
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(private val binding: RecyclerviewEditItemBinding, listener: ItemDragListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.editDragIv.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }


            binding.recyclerEditItemLo.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION && mListener != null) {
                    mListener!!.onItemClick(it, pos)
                    binding.checkCb.isChecked = dataSet[pos].check
                    notifyItemChanged(pos)
                }
            }

        }
        fun bind(data: Stock) {
            binding.name.text = data.name
            //색상 설정
            val color = if (data.currentValue <  data.startValue) ContextCompat.getColor(binding.root.context, R.color.medium_blue)  else ContextCompat.getColor(binding.root.context, R.color.dark_red)
            binding.price.setTextColor(color)
            binding.dif.setTextColor(color)
            binding.difPer.setTextColor(color)
            binding.triangle.setTextColor(color)
            //삼각형 모양설정
            binding.triangle.text = if (data.currentValue < data.startValue) "▼" else "▲"
            //percent 설정
            binding.difPer.text = makePercent((data.currentValue - data.startValue).toDouble() / data.startValue.toDouble() * 100.0)
            //숫자 설정
            binding.dif.text = makeDecimal(data.currentValue - data.startValue)
            binding.price.text = makeDecimal(data.currentValue)
            binding.volume.text = makeDecimal(data.volume)
        }

    }

    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) return
        val fromItem = dataSet.removeAt(from)
        dataSet.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    public interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    public fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }
}