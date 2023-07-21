package com.tnt.beenalone.presentation.edit_display

import java.time.LocalDate

data class EditDisplayUIState(
    val date: LocalDate? = null,
    val title: String? = "Been Alone",
    val startDate: LocalDate? = LocalDate.of(1990, 1, 1)
)