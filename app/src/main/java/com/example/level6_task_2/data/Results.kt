package com.example.level6_task_2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "results")
data class Results(
    @ColumnInfo(name = "result")
    var result: String,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "pcMove")
    var PcMove: Int,

    @ColumnInfo(name = "playerMove")
    var playerMove: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
)
