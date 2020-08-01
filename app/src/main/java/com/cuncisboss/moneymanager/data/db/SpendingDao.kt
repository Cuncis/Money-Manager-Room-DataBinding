package com.cuncisboss.moneymanager.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cuncisboss.moneymanager.model.Spending


@Dao
interface SpendingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(spending: Spending)

    @Query("DELETE FROM speding_table")
    suspend fun deleteAllData()

    @Query("SELECT * FROM speding_table WHERE type =:type")
    fun getAllSpending(type: Int): LiveData<List<Spending>>

    @Update
    suspend fun updateData(spending: Spending)

    @Delete
    suspend fun deleteData(spending: Spending)
}