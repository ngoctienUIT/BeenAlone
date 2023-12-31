package com.tnt.beenalone.presentation.edit_display

import androidx.compose.runtime.getValue
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
import javax.inject.Inject

@HiltViewModel
class EditDisplayViewModel @Inject constructor(
    private val beenAloneRepository: BeenAloneRepository,
    private val dataStoreManager: DataStoreManager
) :
    ViewModel() {
    var title by mutableStateOf("")
    var datePicker: LocalDate by mutableStateOf(LocalDate.now())
    var date: LocalDate by mutableStateOf(LocalDate.now())
    var startDate: LocalDate by mutableStateOf(LocalDate.now())

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    var showToastSuccess by mutableStateOf(false)

    init {
        getSetting()
        getUser()
    }

    private fun getSetting() {
        viewModelScope.launch {
            dataStoreManager.getString("dateAlone").collect { dateAlone ->
                date = LocalDate.parse(dateAlone, formatter)
                datePicker = LocalDate.parse(dateAlone, formatter)
            }
        }
        viewModelScope.launch {
            dataStoreManager.getString("title").collect { myTitle ->
                if (myTitle != null) {
                    title = myTitle
                }
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            beenAloneRepository.getUser().collect {
                if (it != null) {
                    startDate = LocalDate.parse(it.birthday, formatter)
                }
            }
        }
    }

    fun saveSetting(dateAlone: LocalDate, title: String) {
        viewModelScope.launch {
            dataStoreManager.setString("dateAlone", formatter.format(dateAlone))
            showToastSuccess = true
        }
        viewModelScope.launch {
            dataStoreManager.setString("title", title)
        }
    }
}