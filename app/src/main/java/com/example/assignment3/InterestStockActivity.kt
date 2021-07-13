package com.example.assignment3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.databinding.ActivityInterestStockBinding

class InterestStockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInterestStockBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterestStockBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}