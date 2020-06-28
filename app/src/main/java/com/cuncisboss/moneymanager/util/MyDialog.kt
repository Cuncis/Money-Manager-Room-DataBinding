package com.cuncisboss.moneymanager.util

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.cuncisboss.moneymanager.R
import com.cuncisboss.moneymanager.databinding.FragmentMainMoneyBinding

class MyDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val act = activity as FragmentActivity
        val binding = DataBindingUtil.inflate<FragmentMainMoneyBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_nominal,
            null,
            false
        )

        return AlertDialog.Builder(act).setView(binding.root).create()
    }
}