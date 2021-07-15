package com.example.assignment3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.databinding.ActivityInterestStockBinding

const val STOCK_DATA = "stock_data"

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
    private fun initializeDataSet() {
        val defaultArrayList = ArrayList<Stock>()
        defaultArrayList.add(Stock("LG디스플레이", "A034220", "코스피", "korea", false, 23200, 23300, 23200, 23300, 0))
        defaultArrayList.add(Stock("오리온", "A271560", "코스피", "korea", false, 118000, 118000, 118000, 118000, 0))
        defaultArrayList.add(Stock("삼성전자", "A005930","코스피", "korea", false, 79700, 79800, 79700, 79800, 0))
        defaultArrayList.add(Stock("세틀뱅크", "A234340","코스닥", "korea", false, 35800, 35800, 35800, 35800, 0))
        defaultArrayList.add(Stock("RFHIC", "A218410","코스닥", "korea", false, 37550, 37600, 37550, 37600, 0))
        defaultArrayList.add(Stock("쏠리드", "A050890","코스닥", "korea", false, 6950, 7530, 6950, 7530, 0, 1))
        defaultArrayList.add(Stock("피씨엘", "A241820","코스닥", "korea", false, 56400, 56300, 56300, 56400, 0))
        defaultArrayList.add(Stock("씨에스윈드", "A112610","코스피", "korea", false, 76500, 79900, 76500, 79900, 0))
        defaultArrayList.add(Stock("카페24", "A042000","코스닥", "korea", false, 32050, 31500, 31500, 32050, 0))
        defaultArrayList.add(Stock("S-Oil", "A010950","코스피", "korea", false, 105000, 103500, 103500, 105000, 0))
        defaultArrayList.add(Stock("테슬라", "US.TSLA","나스닥", "us", false, 66220, 68570, 66220, 68570, 0))
        defaultArrayList.add(Stock("한화", "A000880","코스피", "korea", false, 30800, 30850, 30800, 30850, 0))
        defaultArrayList.add(Stock("셀트리온헬스케어", "A091990","코스닥", "korea", false, 111800, 112100, 111800, 112100, 0))
        defaultArrayList.add(Stock("한전산업", "A130660","코스피", "korea", false, 16200, 15250, 15250, 16200, 0, 2))
        defaultArrayList.add(Stock("우리들휴브레인", "A118000","코스피", "korea", false, 2090, 2060, 2060, 2090, 0))
        defaultArrayList.add(Stock("펜 버지니아", "US.PVAC","나스닥", "us", false, 2251, 2327, 2251, 2327, 0))
        defaultArrayList.add(Stock("SK이노베이션", "A096770","코스피", "korea", false, 270500, 267000, 267000, 269500, 0))
        defaultArrayList.add(Stock("두산중공업", "A034020","코스피", "korea", false, 25900, 26050, 25750, 26650, 0))
        defaultArrayList.add(Stock("바이오솔루션", "A086820","코스닥", "korea", false, 28400, 28500, 28150, 29000, 0))
        defaultArrayList.add(Stock("도이치모터스", "A067990","코스닥", "korea", false, 7860, 7680, 7650, 7860, 0))
        defaultArrayList.add(Stock("피피아이", "A062970","코스닥", "korea", false, 9740, 9240, 9120, 10300, 0))
        defaultArrayList.add(Stock("삼성SDI", "A006400","코스피", "korea", false, 748000, 750000, 735000, 754000, 0))
        defaultArrayList.add(Stock("삼표시멘트", "A038500","코스닥", "korea", false, 5790, 5790, 5710, 5820, 0))

        for (item in SharedPreferenceManager.getObject(this, STOCK_DATA, defaultArrayList)) {
            stockArrayList.add(item)
        }
    }
}