package com.example.assignment3

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
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

        holder.itemView.setOnClickListener {
            dataSet[position].check = !item.check
            binding.checkCb.isChecked = dataSet[position].check
            notifyDataSetChanged()
        }
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

            /*
            binding.recyclerEditItemLo.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION && mListener != null) {
                    mListener!!.onItemClick(it, pos)
                    notifyDataSetChanged()
                }
            }
            */
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
            binding.checkCb.isChecked = !data.check
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