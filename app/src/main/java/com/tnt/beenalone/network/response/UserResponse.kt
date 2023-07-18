package com.tnt.beenalone.network.response

import com.google.gson.annotations.SerializedName
import com.tnt.beenalone.models.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class UserResponse(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("gender")
    val gender: Boolean,

    @field:SerializedName("birthday")
    val birthday: String,

    @field:SerializedName("avatar")
    val avatar: String,

    @field:SerializedName("dateAlone")
    val dateAlone: String,
) {
    fun toUser(): User {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val myDate = LocalDate.parse(dateAlone.subSequence(0, 10), formatter);
        return User(name, gender, birthday, avatar, myDate)
    }
}
