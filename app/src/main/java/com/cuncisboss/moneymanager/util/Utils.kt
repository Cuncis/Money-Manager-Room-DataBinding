package com.cuncisboss.moneymanager.util

import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


class Utils {
    companion object {

        fun String.replaceUnusedItem(): String {
            return replace("Rp ", "").replace(".", "").replace(" ", "")
        }

        fun EditText.textWatcherNoZero() {
            return this.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().length == 1 && s.toString().startsWith("0")) {
                        s?.clear();
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}