package com.tnt.beenalone.ui.components.toast.type

import androidx.compose.ui.graphics.Color
import com.tnt.beenalone.R
import com.tnt.beenalone.ui.components.toast.CustomToastProperty

class Info : CustomToastProperty {
    override fun getResourceId(): Int = R.drawable.toast_icon_info
    override fun getBackgroundColor(): Color = Color(0xFFF88F01)
}