package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity(), ItemDragListener {
    private lateinit var binding: ActivityEditBinding
    private lateinit var customEditAdapter: CustomRecyclerEditAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    var stockArrayList = ArrayList<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        customEditAdapter = CustomRecyclerEditAdapter(stockArrayList, this)
        //layout manager설정
        binding.editRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.editRecyclerview.adapter = customEditAdapter
        binding.editRecyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //itemTouchHelper선언
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(customEditAdapter))
        itemTouchHelper.attachToRecyclerView(binding.editRecyclerview)

        //종목추가 클릭 리스너
        binding.addStockTv.setOnClickListener {
            val intent = Intent(this, InterestStockActivity::class.java)
            startActivity(intent)
        }

        Log.d("EditActivity", "onCreate()")
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onRestart() {
        super.onRestart()
        stockArrayList.clear()
    }

    override fun onResume() {
        super.onResume()
        //저장된 값 불러와서 초기화
        for (item in SharedPreferenceManager.getObject(this, STOCK_DATA, ArrayList<Stock>())) {
            if (item.check) {
                stockArrayList.add(item)
            }
        }
        customEditAdapter.notifyDataSetChanged()
    }
}