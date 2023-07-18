package com.tnt.beenalone.models

import java.time.LocalDate

data class User(
    val name: String,
    val gender: Boolean,
    val birthday: String,
    val avatar: String,
    val dateAlone: LocalDate,
)
