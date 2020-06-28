package com.cuncisboss.moneymanager.viewmodel

import android.app.Application
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuncisboss.moneymanager.R
import com.cuncisboss.moneymanager.databinding.FragmentMainMoneyBinding
import com.cuncisboss.moneymanager.model.Spending
import com.cuncisboss.moneymanager.repository.SpendingRepository
import com.cuncisboss.moneymanager.util.DateHelper
import com.cuncisboss.moneymanager.util.MyDialog
import kotlinx.android.synthetic.main.dialog_nominal.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpendingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SpendingRepository(application)

    fun getAllSpending(type: Int): LiveData<List<Spending>> {
        return repository.getAllSpending(type)
    }

    fun insertData(spending: Spending) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertData(spending)
    }

    fun deleteAllData() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllData()
    }

}