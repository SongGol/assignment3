package com.example.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment3.databinding.ActivityMainBinding
import com.example.assignment3.databinding.FragmentInterestBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FragmentInterestBinding
    var stockArrayList = ArrayList<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = FragmentInterestBinding.inflate(layoutInflater)
        binding = FragmentInterestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeDataSet()
        //layout manager설정
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainRecyclerview.adapter = CustomRecyclerAdapter(stockArrayList)
        binding.mainRecyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        /*
        binding.addStockBtn.setOnClickListener{
            val intent = Intent(this, InterestStockActivity::class.java)
            startActivity(intent)
        }*/
    }

    fun initializeDataSet() {
        val defaultArrayList = ArrayList<Stock>()
        defaultArrayList.add(Stock("LG디스플레이", "A034220", "코스피", "korea", false, 23200, 23300, 23200, 23300, 0))
        defaultArrayList.add(Stock("오리온", "A271560", "코스피", "korea", false, 118000, 118000, 118000, 118000, 0))
        defaultArrayList.add(Stock("삼성전자", "A005930","코스피", "korea", false, 79700, 79800, 79700, 79800, 0))
        defaultArrayList.add(Stock("세틀뱅크", "A234340","코스닥", "korea", false, 35800, 35800, 35800, 35800, 0))
        defaultArrayList.add(Stock("RFHIC", "A218410","코스닥", "korea", false, 37550, 37600, 37550, 37600, 0))
        defaultArrayList.add(Stock("쏠리드", "A050890","코스닥", "korea", false, 6950, 7530, 6950, 7530, 0))
        defaultArrayList.add(Stock("피씨엘", "A241820","코스닥", "korea", false, 56400, 56300, 56300, 56400, 0))
        defaultArrayList.add(Stock("씨에스윈드", "A112610","코스피", "korea", false, 76500, 79900, 76500, 79900, 0))
        defaultArrayList.add(Stock("카페24", "A042000","코스닥", "korea", false, 32050, 31500, 31500, 32050, 0))
        defaultArrayList.add(Stock("S-Oil", "A010950","코스피", "korea", false, 105000, 103500, 103500, 105000, 0))
        defaultArrayList.add(Stock("테슬라", "US.TSLA","나스닥", "us", false, 66220, 68570, 66220, 68570, 0))
        defaultArrayList.add(Stock("한화", "A000880","코스피", "korea", false, 30800, 30850, 30800, 30850, 0))
        defaultArrayList.add(Stock("셀트리온헬스케어", "A091990","코스닥", "korea", false, 111800, 112100, 111800, 112100, 0))
        defaultArrayList.add(Stock("한전산업", "A130660","코스피", "korea", false, 16200, 15250, 15250, 16200, 0))
        defaultArrayList.add(Stock("우리들휴브레인", "A118000","코스피", "korea", false, 2090, 2060, 2060, 2090, 0))
        defaultArrayList.add(Stock("펜 버지니아", "US.PVAC","나스닥", "us", false, 2251, 2327, 2251, 2327, 0))

        stockArrayList = defaultArrayList
    }
}