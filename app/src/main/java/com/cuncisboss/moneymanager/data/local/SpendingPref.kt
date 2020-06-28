package com.cuncisboss.moneymanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.cuncisboss.moneymanager.util.Constants.PREF_NAME
import com.cuncisboss.moneymanager.util.Constants.TOTAL_ALT_KEY
import com.cuncisboss.moneymanager.util.Constants.TOTAL_MAIN_KEY

class SpendingPref {
    companion object {

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        }

        fun setTotalMain(context: Context, total: Long) {
            val editor = getSharedPreferences(context).edit()
            editor.putLong(TOTAL_MAIN_KEY, total)
            editor.apply()
        }

        fun setTotalAlt(context: Context, total: Long) {
            val editor = getSharedPreferences(context).edit()
            editor.putLong(TOTAL_ALT_KEY, total)
            editor.apply()
        }

        fun getTotalMain(context: Context): Long {
            return getSharedPreferences(context).getLong(TOTAL_MAIN_KEY, 0L)
        }

        fun getTotalAlt(context: Context): Long {
            return getSharedPreferences(context).getLong(TOTAL_ALT_KEY, 0L)
        }

        fun clear(context: Context) {
            val editor = getSharedPreferences(context).edit()
            editor.clear()
            editor.apply()
        }

    }
}