package com.tnt.beenalone.presentation.splash

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.store.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {
    var isFist by mutableStateOf<Boolean?>(null)

    init {
        checkNew()
    }

    private fun checkNew() {
        viewModelScope.launch {
            val check = dataStoreManager.getString("isNew").first()
            isFist = check.isNullOrEmpty()
            dataStoreManager.setString("isNew", "false")
        }
    }
}