package com.cuncisboss.moneymanager.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.cuncisboss.moneymanager.data.db.SpendingDatabase
import com.cuncisboss.moneymanager.model.Spending

class SpendingRepository(private val application: Application) {

    private val spendingDao = SpendingDatabase.getDatabase(application).spendingDao()

    fun getAllSpending(type: Int): LiveData<List<Spending>> {
        return spendingDao.getAllSpending(type)
    }

    suspend fun insertData(spending: Spending) = spendingDao.insertData(spending)

    suspend fun deleteAllData() = spendingDao.deleteAllData()

}