package com.example.assignment3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.databinding.ActivityInterestStockBinding

class InterestStockActivity : AppCompatActivity() {
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

    }




    fun initializeDataSet() {
        stockArrayList.add(Stock("LG디스플레이", "A034220", "코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("오리온", "A271560", "코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("삼성전자", "A005930","코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("세틀뱅크", "A234340","코스닥", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("RFHIC", "A218410","코스닥", "kora", false, 23200, 23300))
        stockArrayList.add(Stock("쏠리드", "A050890","코스닥", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("피씨엘", "A241820","코스닥", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("씨에스윈드", "A112610","코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("카페24", "A042000","코스닥", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("S-Oil", "A010950","코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("테슬라", "US.TSLA","나스닥", "us", false, 23200, 23300))
        stockArrayList.add(Stock("한화", "A000880","코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("셀트리온헬스케어", "A091990","코스닥", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("한전산업", "A130660","코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("우리들휴브레인", "A118000","코스피", "korea", false, 23200, 23300))
        stockArrayList.add(Stock("펜 버지니아", "US.PVAC","나스닥", "us", false, 23200, 23300))
    }
}