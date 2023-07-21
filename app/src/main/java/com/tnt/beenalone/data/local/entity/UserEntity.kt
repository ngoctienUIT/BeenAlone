package com.tnt.beenalone.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tnt.beenalone.models.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.annotation.Nullable

@Entity(tableName = "User")
data class UserEntity(
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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @Nullable
    @ColumnInfo(name = "idMongo")
    val idMongo: String? = null,
) {
    fun toUser(): User {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return User(name, gender, birthday, avatar, LocalDate.parse(dateAlone, formatter))
    }
}