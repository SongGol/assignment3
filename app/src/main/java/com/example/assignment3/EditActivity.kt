package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
    var defaultList = ArrayList<Stock>()
    var stockArrayList = ArrayList<Stock>()
    private var select: Int = 0
    private val NAMES = "names"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //저장된 값 불러와서 초기화
        for (item in SharedPreferenceManager.getObject(this, STOCK_DATA, ArrayList<Stock>())) {
            //defaultList.add(item)
            if (item.check) {
                stockArrayList.add(item)
            } else {
                defaultList.add(item)
            }
        }
        //intent받기
        val pos = intent.getIntExtra("position", -1)
        if (pos != -1) {
            stockArrayList[pos].check = false
        }

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

        //recyclerview item click listener
        customEditAdapter.setOnItemClickListener(object: CustomRecyclerEditAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("EditActivity on clicked position ",position.toString())
                stockArrayList[position].check = !stockArrayList[position].check
            }
        })

        //편집 textview
        binding.editTv.setOnClickListener {
            finish()
        }

        //전체 선택 버튼 클릭 리스너
        binding.selectAllTv.setOnClickListener {
            if (select == 0) {
                for (item in stockArrayList) {
                    item.check = false
                }
                binding.selectAllTv.text = "전체해제"
                select = 1
            } else {
                for (item in stockArrayList) {
                    item.check = true
                }
                binding.selectAllTv.text = "전체선택"
                select = 0
            }
            customEditAdapter.notifyDataSetChanged()
        }

        //삭제 버튼 클릭 리스너
        binding.deleteTv.setOnClickListener {
            //아무것도 안눌려있으면 삭제할 관심종목을 선택해 주세요 토스트 메시지
            var checkBool = false
            for (item in stockArrayList) {
                if (!item.check) checkBool = true
            }
            if (checkBool) {
                var removeItems = ArrayList<Stock>()
                for (item in stockArrayList) {
                    if (!item.check) {
                        removeItems.add(item)
                    }
                }
                defaultList = ArrayList(defaultList + removeItems)
                stockArrayList.removeAll(removeItems)
                finish()
            } else {
                Toast.makeText(this, "삭제할 관심종목을 선택해 주세요", Toast.LENGTH_SHORT).show()
            }
        }

        //완료버튼 클릭 리스너
        binding.completeTv.setOnClickListener {
            for (item in stockArrayList) {
                item.check = true
            }
            finish()
        }

        Log.d("EditActivity", "onCreate()")
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onRestart() {
        super.onRestart()/*
        stockArrayList.clear()
        //저장된 값 불러와서 초기화
        for (item in SharedPreferenceManager.getObject(this, STOCK_DATA, ArrayList<Stock>())) {
            if (item.check) {
                stockArrayList.add(item)
            }
        }*/

        var index = 0
        val names = SharedPreferenceManager.getObject(this, NAMES, ArrayList<Stock>())
        //저장된 값 불러와서 초기화
        stockArrayList.clear()
        defaultList.clear()
        for (item in SharedPreferenceManager.getObject(this, STOCK_DATA, ArrayList<Stock>())) {
            //defaultList.add(item)
            if (item.check) {
                if (index != names.size && item.name == names[index].name) {
                    item.check = false
                    index++
                }
                stockArrayList.add(item)
            } else {
                defaultList.add(item)
            }
        }
        customEditAdapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        //check에 해당하는 항목 저장
        var names = ArrayList<Stock>()
        for (item in stockArrayList) {
            if (!item.check) {
                item.check = true
                names.add(item)
            }
        }
        SharedPreferenceManager.putObject(this, NAMES, names)

        for (item in ArrayList<Stock>(stockArrayList + defaultList)) {
            Log.d("EditActivity onPause()", item.name+", "+item.check.toString())
        }

        SharedPreferenceManager.putObject(this, STOCK_DATA, ArrayList<Stock>(stockArrayList + defaultList))
    }

    override fun onStop() {
        super.onStop()
        for (item in stockArrayList) {
            Log.d("EditActivity onStop", item.name+", "+item.check.toString())
        }


    }

    override fun onResume() {
        super.onResume()

    }
}