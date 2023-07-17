package com.tnt.beenalone.network.response

import com.google.gson.annotations.SerializedName

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
)
