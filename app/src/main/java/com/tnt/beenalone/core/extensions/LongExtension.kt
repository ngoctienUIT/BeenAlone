package com.tnt.beenalone.core.extensions

fun Long.toRankIndex(): Int {
    return if (this.div(100) < 25) {
        this.div(100).toInt()
    } else if (this.div(100) < 30) {
        25
    } else {
        26
    }
}

fun Long.toNumberStar(): Int {
    return if (this.div(100) > 24) {
        val number = this - 2500
        number.div(10).toInt()
    } else {
        0
    }
}