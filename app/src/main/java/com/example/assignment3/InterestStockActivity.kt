package com.example.assignment3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.databinding.ActivityInterestStockBinding

class InterestStockActivity : AppCompatActivity() {
    private val STOCK_DATA = "stock_data"
    private lateinit var binding: ActivityInterestStockBinding
    private lateinit var customAdapter: CustomListAdapter
    var stockArrayList = ArrayList<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterestStockBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeDataSet()
        customAdapter = CustomListAdapter(this, stockArrayList)
        binding.interestLv.adapter = customAdapter

        binding.interestLv.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as Stock
            element.check = !element.check
            customAdapter.notifyDataSetChanged()
        }

        binding.interestSaveTv.setOnClickListener {
            //체크 내용 저장 및 종료
            SharedPreferenceManager.putObject(this, STOCK_DATA, stockArrayList)
            finish()
        }


    }

    //항목 추가 후 SharedPreferences에 저장된 동일 이름의 항목은 check = true표시
    fun initializeDataSet() {
        val defaultArrayList = ArrayList<Stock>()
        defaultArrayList.add(Stock("LG디스플레이", "A034220", "코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("오리온", "A271560", "코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("삼성전자", "A005930","코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("세틀뱅크", "A234340","코스닥", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("RFHIC", "A218410","코스닥", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("쏠리드", "A050890","코스닥", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("피씨엘", "A241820","코스닥", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("씨에스윈드", "A112610","코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("카페24", "A042000","코스닥", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("S-Oil", "A010950","코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("테슬라", "US.TSLA","나스닥", "us", false, 23200, 23300))
        defaultArrayList.add(Stock("한화", "A000880","코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("셀트리온헬스케어", "A091990","코스닥", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("한전산업", "A130660","코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("우리들휴브레인", "A118000","코스피", "korea", false, 23200, 23300))
        defaultArrayList.add(Stock("펜 버지니아", "US.PVAC","나스닥", "us", false, 23200, 23300))

        for (item in SharedPreferenceManager.getObject(this, STOCK_DATA, defaultArrayList)) {
            stockArrayList.add(item)
        }
    }
}