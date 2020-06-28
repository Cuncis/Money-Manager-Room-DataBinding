package com.cuncisboss.moneymanager.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cuncisboss.moneymanager.model.Spending


@Database(
    entities = [Spending::class],
    version = 1,
    exportSchema = false
)
abstract class SpendingDatabase: RoomDatabase() {

    abstract fun spendingDao(): SpendingDao

    companion object {
        @Volatile
        var INSTANCE: SpendingDatabase? = null

        fun getDatabase(context: Context): SpendingDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpendingDatabase::class.java,
                    "spending.db").build()
                INSTANCE = instance
                instance
            }
        }
    }

}