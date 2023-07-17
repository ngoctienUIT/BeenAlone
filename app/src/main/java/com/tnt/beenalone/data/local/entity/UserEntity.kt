package com.tnt.beenalone.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @Nullable
    @ColumnInfo(name = "idMongo")
    val idMongo: String? = null,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "gender")
    val gender: Boolean,

    @ColumnInfo(name = "birthday")
    val birthday: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "dateAlone")
    val dateAlone: String,
)