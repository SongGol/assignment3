package com.example.assignment3

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.assignment3.databinding.DialogSortBinding
import java.lang.ClassCastException

class SortDialog : DialogFragment() {
    private lateinit var binding: DialogSortBinding
    private var mListener: OnSortListener? = null

    companion object {
        const val SORT_KEY = "sort_key"
        const val SORT_NOT ="sort_not"
        const val SORT_PER = "sort_per"
        const val SORT_DIF = "sort_dif"
        const val SORT_VOLUME = "sort_volume"
        const val SORT_PRICE = "sort_price"
        const val SORT_BENEFIT_RATIO = "sort_benefit_ratio"
        const val SORT_BENEFIT_PRICE = "sort_benefit_price"
        const val SORT_BUY = "sort_buy"
        const val SORT_TRADE = "sort_trade"
        const val SORT_NAME = "sort_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSortBinding.inflate(inflater, container, false)

        //type에 따라 색설정
        val sortType = SharedPreferenceManager.getStrValue(activity, SORT_KEY, SORT_NOT)
        when(sortType) {
            SORT_NOT -> binding.sortNotTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_PER -> binding.sortPerTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_DIF -> binding.sortDifTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_VOLUME -> binding.sortVolumeTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_PRICE -> binding.sortPriceTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_BENEFIT_RATIO -> binding.sortBenefitRatioTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_BENEFIT_PRICE -> binding.sortBenefitPriceTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_BUY -> binding.sortBuyTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_TRADE -> binding.sortTradeTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
            SORT_NAME -> binding.sortNameTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.dark_red))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sortNotTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_NOT)
            this.dismiss()
            mListener?.onSortSet(SORT_NOT)
        }

        binding.sortPerTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_PER)
            this.dismiss()
            mListener?.onSortSet(SORT_PER)
        }

        binding.sortDifTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_DIF)
            this.dismiss()
            mListener?.onSortSet(SORT_DIF)
        }

        binding.sortVolumeTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_VOLUME)
            this.dismiss()
            mListener?.onSortSet(SORT_VOLUME)
        }

        binding.sortPriceTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_PRICE)
            this.dismiss()
            mListener?.onSortSet(SORT_PRICE)
        }

        binding.sortBenefitRatioTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_BENEFIT_RATIO)
            this.dismiss()
            mListener?.onSortSet(SORT_BENEFIT_RATIO)
        }

        binding.sortBenefitPriceTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_BENEFIT_PRICE)
            this.dismiss()
            mListener?.onSortSet(SORT_BENEFIT_PRICE)
        }

        binding.sortBuyTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_BUY)
            this.dismiss()
            mListener?.onSortSet(SORT_BUY)
        }

        binding.sortTradeTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_TRADE)
            this.dismiss()
            mListener?.onSortSet(SORT_TRADE)
        }

        binding.sortNameTv.setOnClickListener {
            SharedPreferenceManager.putStrValue(activity, SORT_KEY, SORT_NAME)
            this.dismiss()
            mListener?.onSortSet(SORT_NAME)
        }

        binding.sortCloseTv.setOnClickListener {
            this.dismiss()
        }
    }

    interface OnSortListener {
        fun onSortSet(type: String)
    }

    public fun setOnSortListener(listener: OnSortListener) {
        mListener = listener
    }
}