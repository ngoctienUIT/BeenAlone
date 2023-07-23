package com.tnt.beenalone.presentation.home

import com.tnt.beenalone.models.User
import java.time.LocalDate

data class HomeUIState(
    val date: LocalDate? = LocalDate.now(),
    val title: String? = null,
    val user: User? = null
)