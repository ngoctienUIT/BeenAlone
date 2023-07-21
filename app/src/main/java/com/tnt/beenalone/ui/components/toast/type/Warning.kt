package com.tnt.beenalone.ui.components.toast.type

import androidx.compose.ui.graphics.Color
import com.tnt.beenalone.R
import com.tnt.beenalone.ui.components.toast.CustomToastProperty

class Warning : CustomToastProperty {
    override fun getResourceId(): Int = R.drawable.toast_icon_warning
    override fun getBackgroundColor(): Color = Color(0xFF0070E0)
}