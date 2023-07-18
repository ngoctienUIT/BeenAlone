package com.tnt.beenalone.presentation.rank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.network.api.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(private val userApi: UserApi) : ViewModel() {
    private val _rankUIState = MutableStateFlow(RankUIState())
    val rankUIState: StateFlow<RankUIState> = _rankUIState

    init {
        getRank()
    }

    private fun getRank() {
        viewModelScope.launch {
            val response = userApi.getRank()
            if (response.isSuccessful) {
                _rankUIState.value = RankUIState(response.body()!!.map { item -> item.toUser() })
            }
        }
    }
}