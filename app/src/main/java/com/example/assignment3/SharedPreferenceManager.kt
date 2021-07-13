package com.example.assignment3

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferenceManager {
    private const val NAME = "login_pref"
    private const val MODE = Context.MODE_PRIVATE

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(NAME, MODE)
    }

    fun getStrValue(context: Context?, KEY: String, defaultValue: String? = null): String? {
        return getSharedPreferences(context!!).getString(KEY, defaultValue)
    }

    fun getIntValue(context: Context?, KEY: String, defaultValue: Int? = 0): Int? {
        return defaultValue?.let { getSharedPreferences(context!!).getInt(KEY, it)}
    }

    fun getBoolValue(context: Context?, KEY: String, defaultValue: Boolean? = false): Boolean? {
        return defaultValue?.let { getSharedPreferences(context!!).getBoolean(KEY, it)}
    }

    fun putStrValue(context: Context?, KEY: String, valueString: String?) {
        val editor = getSharedPreferences(context!!).edit()
        editor.putString(KEY, valueString)
        editor.apply()
    }

    fun putIntValue(context: Context?, KEY: String, valueInt: Int?) {
        val editor = getSharedPreferences(context!!).edit()
        valueInt?.let {
            editor.putInt(KEY, it)
        }
        editor.apply()
    }

    fun putBoolValue(context: Context?, KEY: String, valueBool: Boolean?) {
        val editor = getSharedPreferences(context!!).edit()
        valueBool?.let {
            editor.putBoolean(KEY, it)
        }
        editor.apply()
    }

    fun putObject(context: Context?, KEY: String, valueObject: ArrayList<Stock>) {
        val editor = getSharedPreferences(context!!).edit()
        val gson = Gson()
        val json: String = gson.toJson(valueObject)
        editor.putString(KEY, json)
        editor.apply()
    }

    fun getObject(context: Context?, KEY: String, defaultValue: ArrayList<Stock>): ArrayList<Stock> {
        val gson = Gson()
        val mPref = getSharedPreferences(context!!)
        val json: String? = mPref.getString(KEY, gson.toJson(defaultValue))

        val itemType = object : TypeToken<List<Stock>>() {}.type
        return gson.fromJson<ArrayList<Stock>>(json, itemType)
    }

    fun isContain(context: Context?, KEY: String): Boolean {
        val editor = getSharedPreferences(context!!)
        return editor.contains(KEY)
    }

    fun clearAllValue(context: Context?) {
        val editor = getSharedPreferences(context!!).edit()
        editor.clear()
        editor.apply()
    }

    fun removeItem(context: Context?, KEY: String) {
        val editor = getSharedPreferences(context!!).edit()
        editor.remove(KEY)
        editor.apply()
    }
}