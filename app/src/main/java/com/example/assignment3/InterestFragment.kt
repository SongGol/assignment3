package com.example.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment3.databinding.FragmentInterestBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timer

class InterestFragment : Fragment() {
    private lateinit var binding: FragmentInterestBinding
    private lateinit var customRecyclerAdapter: CustomRecyclerAdapter
    private lateinit var customRecyclerGridAdapter: CustomRecyclerGridAdapter
    var stockArrayList = ArrayList<Stock>()
    var defaultArrayList = ArrayList<Stock>()
    private var i: Int = 0

    //타이머를 위한 변수
    private var mTimerTask: Timer? = null

    //layout저장을 위한 상수
    private val LAYOUT_TYPE = "layout_type"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customRecyclerAdapter = CustomRecyclerAdapter(stockArrayList)
        customRecyclerGridAdapter = CustomRecyclerGridAdapter(stockArrayList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInterestBinding.inflate(inflater, container, false)

        //layout manager설정
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.mainRecyclerview.adapter = customRecyclerAdapter
        binding.mainRecyclerview.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))


        //종목 표시창 변경 리스너
        binding.mainGridIv.setOnClickListener {
            if (i == 0) {
                binding.mainGridIv.setImageResource(R.drawable.linear)
                //grid manager설정
                binding.mainRecyclerview.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
                binding.mainRecyclerview.adapter = customRecyclerGridAdapter
                i = 1
            } else {
                binding.mainGridIv.setImageResource(R.drawable.grid)
                //layout manager설정
                binding.mainRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.mainRecyclerview.adapter = customRecyclerAdapter
                i = 0
            }
        }

        //관심종목 추가 리스너
        binding.addStockBtn.setOnClickListener{
            val intent = Intent(activity, InterestStockActivity::class.java)
            startActivity(intent)
        }
        //종목추가 리스너
        binding.mainStockAddTv.setOnClickListener{
            val intent = Intent(activity, InterestStockActivity::class.java)
            startActivity(intent)
        }
        //종목 설정 리스너
        binding.mainSettingIv.setOnClickListener {
            Log.d("InterestFragment","setting iv clicked")
            val intent = Intent(activity, EditActivity::class.java)
            startActivity(intent)
        }

        customRecyclerAdapter.setOnItemLongClickListener(object: CustomRecyclerAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int) {
                Log.d("InterestFragment item long click", position.toString())
                val intent = Intent(activity, EditActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("InterestFragment", "onStart()")
        startTimer()
    }

    override fun onResume() {
        super.onResume()

        stockArrayList.clear()
        defaultArrayList.clear()
        for (item in SharedPreferenceManager.getObject(activity, STOCK_DATA, initialDataSet())) {
            if (item.check) {
                stockArrayList.add(item)
            } else {
                defaultArrayList.add(item)
            }
        }
        customRecyclerAdapter.notifyDataSetChanged()
        customRecyclerGridAdapter.notifyDataSetChanged()

        Log.d("InterestFragment", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("InterestFragment", "onPause()")
        SharedPreferenceManager.putObject(activity, STOCK_DATA, ArrayList<Stock>(stockArrayList + defaultArrayList))
    }

    override fun onStop() {
        super.onStop()
        Log.d("InterestFragment", "onStop()")
        mTimerTask?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("InterestFragment", "onDestroy()")
    }

    private fun startTimer() {
        val random = Random()
        mTimerTask = fixedRateTimer(period = 1000) {
            activity?.runOnUiThread {
                for (item in stockArrayList) {
                    val vol = random.nextInt(1000)
                    val num = (random.nextInt(11) - 5) * 50
                    item.volume += vol
                    setCurrentPrice(item, num)
                }
                for (item in defaultArrayList) {
                    val vol = random.nextInt(1000)
                    val num = (random.nextInt(11) - 5) * 50
                    item.volume += vol
                    setCurrentPrice(item, num)
                }
                //저가 고가는 갱신하지는 않음
                customRecyclerAdapter.notifyDataSetChanged()
                customRecyclerGridAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setCurrentPrice(stock: Stock, num: Int) {
        val nextPrice = stock.currentValue + num
        if (stock.startValue.toDouble()*0.7 <= nextPrice && nextPrice <= stock.startValue.toDouble()*1.3) {
            stock.currentValue = nextPrice
        }
    }
}

fun initialDataSet() : ArrayList<Stock> {
    var default = ArrayList<Stock>()
    default.add(Stock("LG디스플레이", "A034220", "코스피", "korea", false, 23200, 23300, 23200, 23300, 0))
    default.add(Stock("오리온", "A271560", "코스피", "korea", false, 118000, 118000, 118000, 118000, 0))
    default.add(Stock("삼성전자", "A005930","코스피", "korea", false, 79700, 79800, 79700, 79800, 0))
    default.add(Stock("세틀뱅크", "A234340","코스닥", "korea", false, 35800, 35800, 35800, 35800, 0))
    default.add(Stock("RFHIC", "A218410","코스닥", "korea", false, 37550, 37600, 37550, 37600, 0))
    default.add(Stock("쏠리드", "A050890","코스닥", "korea", false, 6950, 7530, 6950, 7530, 0, 1))
    default.add(Stock("피씨엘", "A241820","코스닥", "korea", false, 56400, 56300, 56300, 56400, 0))
    default.add(Stock("씨에스윈드", "A112610","코스피", "korea", false, 76500, 79900, 76500, 79900, 0))
    default.add(Stock("카페24", "A042000","코스닥", "korea", false, 32050, 31500, 31500, 32050, 0))
    default.add(Stock("S-Oil", "A010950","코스피", "korea", false, 105000, 103500, 103500, 105000, 0))
    default.add(Stock("테슬라(미국)", "US.TSLA","나스닥", "us", false, 66220, 68570, 66220, 68570, 0))
    default.add(Stock("한화", "A000880","코스피", "korea", false, 30800, 30850, 30800, 30850, 0))
    default.add(Stock("셀트리온헬스케어", "A091990","코스닥", "korea", false, 111800, 112100, 111800, 112100, 0))
    default.add(Stock("한전산업", "A130660","코스피", "korea", false, 16200, 15250, 15250, 16200, 0, 2))
    default.add(Stock("우리들휴브레인", "A118000","코스피", "korea", false, 2090, 2060, 2060, 2090, 0))
    default.add(Stock("펜 버지니아(미국)", "US.PVAC","나스닥", "us", false, 2251, 2327, 2251, 2327, 0))
    default.add(Stock("SK이노베이션", "A096770","코스피", "korea", false, 270500, 267000, 267000, 269500, 0))
    default.add(Stock("두산중공업", "A034020","코스피", "korea", false, 25900, 26050, 25750, 26650, 0))
    default.add(Stock("바이오솔루션", "A086820","코스닥", "korea", false, 28400, 28500, 28150, 29000, 0))
    default.add(Stock("도이치모터스", "A067990","코스닥", "korea", false, 7860, 7680, 7650, 7860, 0))
    default.add(Stock("피피아이", "A062970","코스닥", "korea", false, 9740, 9240, 9120, 10300, 0))
    default.add(Stock("삼성SDI", "A006400","코스피", "korea", false, 748000, 750000, 735000, 754000, 0))
    default.add(Stock("삼표시멘트", "A038500","코스닥", "korea", false, 5790, 5790, 5710, 5820, 0))

    return default
}