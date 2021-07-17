package com.example.assignment3

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.databinding.RecyclerviewItemBinding
import java.text.DecimalFormat

class CustomRecyclerAdapter(var dataSet: ArrayList<Stock>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){
    private lateinit var binding: RecyclerviewItemBinding
    private var mLongListener: OnItemLongClickListener? = null

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

    inner class ViewHolder(private val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.recyclerItemLo.setOnLongClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION && mLongListener != null) {
                    mLongListener!!.onItemLongClick(it, pos)
                    true
                }
                false
            }
        }

        fun bind(data: Stock) {
            //종목정보
            binding.recyclerItemStatus.setImageResource(if(data.status == 0) 0 else if (data.status == 1) R.drawable.management else R.drawable.warning)
            //종목명
            binding.recyclerItemName.text = data.name
            //색상 설정
            val color = if (data.currentValue <  data.startValue) ContextCompat.getColor(binding.root.context, R.color.medium_blue)  else ContextCompat.getColor(binding.root.context, R.color.dark_red)
            binding.recyclerItemPrice.setTextColor(color)
            binding.recyclerItemDif.setTextColor(color)
            binding.recyclerItemDifPer.setTextColor(color)
            binding.recyclerItemTriangle.setTextColor(color)
            //삼각형 모양설정
            binding.recyclerItemTriangle.text = if (data.currentValue < data.startValue) "▼" else "▲"
            //percent 설정
            binding.recyclerItemDifPer.text = makePercent((data.currentValue - data.startValue).toDouble() / data.startValue.toDouble() * 100.0)
            //숫자 설정
            binding.recyclerItemDif.text = makeDecimal(data.currentValue - data.startValue)
            binding.recyclerItemPrice.text = makeDecimal(data.currentValue)
            binding.recyclerItemVolume.text = makeDecimal(data.volume)
        }
    }

    public interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int)
    }

    public fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.mLongListener = listener
    }
}

fun makeDesign(stock: Stock, binding: RecyclerviewItemBinding, context: Context)
{
    binding.recyclerItemName.text = stock.name
    //색상 설정
    val color = if (stock.currentValue <  stock.startValue) ContextCompat.getColor(context, R.color.medium_blue)  else ContextCompat.getColor(context, R.color.dark_red)
    binding.recyclerItemPrice.setTextColor(color)
    binding.recyclerItemDif.setTextColor(color)
    binding.recyclerItemDifPer.setTextColor(color)
    binding.recyclerItemTriangle.setTextColor(color)
    //삼각형 모양설정
    binding.recyclerItemTriangle.text = if (stock.currentValue < stock.startValue) "▼" else "▲"
}

fun makePercent(amount: Double) : String
{
    val value = if (amount < 0) -amount else amount
    val formatter = DecimalFormat("#0.00")
    return formatter.format(value)+"%"
}

fun makeDecimal(amount: Int) : String
{
    val value = if (amount < 0) -amount else amount
    val formatter = DecimalFormat("###,###,###")
    return formatter.format(value)
}