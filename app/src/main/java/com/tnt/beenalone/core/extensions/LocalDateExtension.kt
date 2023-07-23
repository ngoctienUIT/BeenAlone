package com.tnt.beenalone.core.extensions

import java.time.LocalDate
import java.time.YearMonth

fun LocalDate.previousMonth(): LocalDate {
    return LocalDate.of(
        if (this.monthValue == 1) this.year - 1 else this.year,
        if (this.monthValue == 1) 12 else this.monthValue - 1,
        1
    )
}

fun LocalDate.nextMonth(): LocalDate {
    return LocalDate.of(
        if (this.monthValue == 12) this.year + 1 else this.year,
        if (this.monthValue == 12) 1 else this.monthValue + 1,
        1
    )
}

fun LocalDate.lastDate(): LocalDate = YearMonth.from(this).atEndOfMonth()
