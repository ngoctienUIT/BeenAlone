package com.tnt.beenalone.models

import com.tnt.beenalone.data.local.entity.UserEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class User(
    val name: String,
    val gender: Boolean,
    val birthday: String,
    val avatar: String,
    val dateAlone: LocalDate,
) {
    fun toUserEntity(idMongo: String? = null, id: Long = 0): UserEntity {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return UserEntity(name, gender, birthday, avatar, formatter.format(dateAlone), id, idMongo)
    }
}
