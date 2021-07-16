package com.example.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment3.databinding.ActivityMainBinding
import com.example.assignment3.databinding.FragmentInterestBinding

const val INTEREST = "interest"
const val COMMUNITY = "community"
const val TREND = "trend"
const val DISCOVER = "discover"
const val MORE = "more"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private var CUR = INTEREST
    private var backPressedTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction().apply {
            replace(binding.mainFragment.id, InterestFragment())
            addToBackStack(INTEREST)
            commit()
        }

        //onclick listener설정
        //관심종목
        binding.mainInterestFrgIv.setOnClickListener {
            supportFragmentManager.popBackStack(CUR, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            CUR = INTEREST

            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainFragment.id, InterestFragment())
                addToBackStack(CUR)
                commit()
            }
        }

        //더보기
        binding.mainMoreIv.setOnClickListener {
            supportFragmentManager.popBackStack(CUR, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            CUR = MORE

            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainFragment.id, MoreFragment())
                addToBackStack(CUR)
                commit()
            }
        }


    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backPressedTime + 2000) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            finish()
            System.exit(0)
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}