package com.tnt.beenalone.presentation.home

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.data.local.store.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val beenAloneRepository: BeenAloneRepository,
    private val dataStoreManager: DataStoreManager
) :
    ViewModel() {
    var days by mutableLongStateOf(0L)
    var title by mutableStateOf("")
    var name by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)

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
                days = ChronoUnit.DAYS.between(
                    LocalDate.parse(dateAlone ?: "11/02/2002", formatter),
                    LocalDate.now()
                )
            }
        }
        viewModelScope.launch {
            dataStoreManager.getString("title").collect { myTitle ->
                Log.d("title", myTitle.toString())
                if (myTitle != null) {
                    title = myTitle
                }
            }
        }
        viewModelScope.launch {
            beenAloneRepository.getUser().collect {
                Log.d("user", it.toString())
                if (it != null) {
                    name = it.name
                    imageUri = if (it.avatar.isNotEmpty()) Uri.parse(it.avatar) else null
                }
            }
        }
    }
}