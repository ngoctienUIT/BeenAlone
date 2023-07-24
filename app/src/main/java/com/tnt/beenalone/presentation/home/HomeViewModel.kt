package com.tnt.beenalone.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.data.local.store.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val beenAloneRepository: BeenAloneRepository,
    private val dataStoreManager: DataStoreManager
) :
    ViewModel() {
    private val _homeUIState = MutableStateFlow(HomeUIState())
    val homeUIState: StateFlow<HomeUIState> = _homeUIState

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    init {
        getSetting()
//        viewModelScope.launch {
//            dataStoreManager.setString("dateAlone", "11/02/2002")
//        }
    }

    private fun getSetting() {
        viewModelScope.launch {
            dataStoreManager.getString("dateAlone").collect { dateAlone ->
                _homeUIState.value =
                    _homeUIState.value.copy(
                        date = LocalDate.parse(dateAlone ?: "11/02/2002", formatter)
                    )
            }
        }
        viewModelScope.launch {
            dataStoreManager.getString("title").collect { title ->
                Log.d("title", title.toString())
                if (title != null) {
                    _homeUIState.value = _homeUIState.value.copy(title = title)
                }
            }
        }
        viewModelScope.launch {
            beenAloneRepository.getUser().collect {
                Log.d("user", it.toString())
                if (it != null) {
                    _homeUIState.value = _homeUIState.value.copy(user = it.toUser())
                }
            }
        }
    }
}