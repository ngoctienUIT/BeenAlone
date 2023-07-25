package com.tnt.beenalone.presentation.rank

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.data.local.store.DataStoreManager
import com.tnt.beenalone.network.api.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val userApi: UserApi,
    private val beenAloneRepository: BeenAloneRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _rankUIState = MutableStateFlow(RankUIState())
    val rankUIState: StateFlow<RankUIState> = _rankUIState

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

//    init {
//        getRank()
//        getUser()
//    }

    private fun getRank() {
        viewModelScope.launch {
            val response = userApi.getRank()
            if (response.isSuccessful) {
                _rankUIState.value = _rankUIState.value.copy(
                    listUser = response.body()!!.map { item -> item.toUser() })
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            dataStoreManager.getString("dateAlone").collect { dateAlone ->
                _rankUIState.value =
                    _rankUIState.value.copy(date = LocalDate.parse(dateAlone, formatter))
            }
        }
        viewModelScope.launch {
            beenAloneRepository.getUser().collect {
                Log.d("user", it.toString())
                if (it != null) {
                    _rankUIState.value = _rankUIState.value.copy(user = it.toUser())
                }
            }
        }
    }
}