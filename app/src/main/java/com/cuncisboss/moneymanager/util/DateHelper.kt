package com.cuncisboss.moneymanager.util

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    companion object {

        fun getCurrentDatetime(): String {
            return SimpleDateFormat("E, dd MMM yyyy HH:mm", Locale.US).format(Date())
        }

    }
}