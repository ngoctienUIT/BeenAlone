package com.tnt.beenalone.ui.components.toast

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.*
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

class CustomToast(context: Context) : Toast(context) {
    @Composable
    fun MakeTest(
        message: String,
        duration: Int = LENGTH_LONG,
        type: CustomToastProperty,
        padding: PaddingValues,
        contentAlignment: Alignment
    ) {
        val context = LocalContext.current

        val views = ComposeView(context)
        views.setContent {
            CustomToastUtil.SetView(
                messageTxt = message,
                resourceIcon = type.getResourceId(),
                backgroundColor = type.getBackgroundColor(),
                padding = padding,
                contentAlignment = contentAlignment
            )
        }

        views.setViewTreeLifecycleOwner(LocalLifecycleOwner.current)
        views.setViewTreeViewModelStoreOwner(LocalViewModelStoreOwner.current)
        views.setViewTreeSavedStateRegistryOwner(LocalSavedStateRegistryOwner.current)

        this.duration = duration
        this.view = views
    }
}