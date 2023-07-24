package com.tnt.beenalone.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "Diary")
data class DiaryEntity(
    @ColumnInfo(name = "feel")
    val feel: Int,

    @Nullable
    @ColumnInfo(name = "content")
    val content: String?,

    @ColumnInfo(name = "day")
    val day: Int,

    @ColumnInfo(name = "month")
    val month: Int,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "time")
    val time: String,

//    @ColumnInfo(name = "created_at")
//    val createdAt: String,
//
//    @ColumnInfo(name = "updated_at")
//    val updatedAt: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
)
