package com.example.assignment3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment3.databinding.FragmentInterestBinding

class InterestFragment : Fragment() {
    private lateinit var binding: FragmentInterestBinding
    private lateinit var customRecyclerAdapter: CustomRecyclerAdapter
    private lateinit var customRecyclerGridAdapter: CustomRecyclerGridAdapter
    var stockArrayList = ArrayList<Stock>()
    private var i: Int = 0

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

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("InterestFragment", "onStart()")
    }

    override fun onResume() {
        super.onResume()

        stockArrayList.clear()
        //initializeDataSet()
        for (item in SharedPreferenceManager.getObject(activity, STOCK_DATA, ArrayList<Stock>())) {
            if (item.check) {
                stockArrayList.add(item)
            }
        }
        customRecyclerAdapter.notifyDataSetChanged()
        customRecyclerGridAdapter.notifyDataSetChanged()

        Log.d("InterestFragment", "onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("InterestFragment", "onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("InterestFragment", "onDestroy()")
    }
}