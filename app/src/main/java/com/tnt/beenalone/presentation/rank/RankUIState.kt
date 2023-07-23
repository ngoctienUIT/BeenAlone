package com.tnt.beenalone.presentation.rank

import com.tnt.beenalone.models.User
import java.time.LocalDate

data class RankUIState(
    val listUser: List<User> = listOf(),
    val date: LocalDate? = LocalDate.now(),
    val user: User? = null
)