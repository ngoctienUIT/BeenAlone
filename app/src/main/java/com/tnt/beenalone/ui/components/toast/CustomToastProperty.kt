package com.tnt.beenalone.ui.components.toast

import androidx.compose.ui.graphics.Color

interface CustomToastProperty {
    fun getResourceId(): Int
    fun getBackgroundColor(): Color
}