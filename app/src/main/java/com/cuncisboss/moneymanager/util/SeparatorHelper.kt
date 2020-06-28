package com.cuncisboss.moneymanager.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class SeparatorHelper {
    companion object {

        fun longToString(nominal: Long): String {
            return String.format("%,d", nominal).replace(',', '.')
        }

    }

    @BindingAdapter("separator_text")
    fun TextView.setSeparator(value: Long) {
        this.text = value.toString()
    }
}