package com.tnt.beenalone.presentation.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(private val beenAloneRepository: BeenAloneRepository) :
    ViewModel() {
    private val _diaryUIState = MutableStateFlow(DiaryUIState())
    val diaryUIState: StateFlow<DiaryUIState> = _diaryUIState

    init {
        getDiary()
    }

    private fun getDiary() {
        viewModelScope.launch {
            beenAloneRepository.getAllDiary().collect {
                _diaryUIState.value = DiaryUIState(it)
            }
        }
    }
}