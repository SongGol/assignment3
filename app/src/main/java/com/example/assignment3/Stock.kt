package com.example.assignment3

data class Stock(val name: String, val code: String, var marketName: String,
                 val country: String, var check: Boolean,
                 var startValue: Int, var currentValue: Int,
                 var lowValue: Int, var highValue: Int, var volume: Int, var status: Int = 0)
