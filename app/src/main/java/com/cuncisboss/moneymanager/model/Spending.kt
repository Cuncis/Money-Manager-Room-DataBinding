package com.cuncisboss.moneymanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "speding_table")
data class Spending(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "nominal")
    var nominal: Long,

    @ColumnInfo(name = "type")
    var type: Int? = null,      // 1 = main, 2 = alt

    @ColumnInfo(name = "datetime")
    var datetime: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}