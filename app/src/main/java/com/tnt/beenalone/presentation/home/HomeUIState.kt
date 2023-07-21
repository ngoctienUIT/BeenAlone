package com.tnt.beenalone.presentation.home

import com.tnt.beenalone.models.User
import java.time.LocalDate

data class HomeUIState(
    val date: LocalDate? = LocalDate.now(),
    val title: String = "Been Alone",
    val user: User? = null,
    val init: Boolean = true,
)