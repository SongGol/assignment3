package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment3.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var customEditAdapter: CustomRecyclerEditAdapter
    var stockArrayList = ArrayList<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //저장된 값 불러와서 초기화
        for (item in SharedPreferenceManager.getObject(this, STOCK_DATA, ArrayList<Stock>())) {
            if (item.check) {
                stockArrayList.add(item)
            }
        }

        customEditAdapter = CustomRecyclerEditAdapter(stockArrayList)
        //layout manager설정
        binding.editRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.editRecyclerview.adapter = customEditAdapter
        binding.editRecyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //종목추가 클릭 리스너
        binding.addStockTv.setOnClickListener {
            val intent = Intent(this, InterestStockActivity::class.java)
            startActivity(intent)
        }

        Log.d("EditActivity", "onCreate()")
    }
}