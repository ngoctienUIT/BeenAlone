package com.tnt.beenalone.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(private val beenAloneRepository: BeenAloneRepository) :
    ViewModel() {
    private val _diaryDateUIState = MutableStateFlow(DiaryDateUIState())
    val diaryDateUIState: StateFlow<DiaryDateUIState> = _diaryDateUIState

    private val _diaryMonthUIState = MutableStateFlow(DiaryMonthUIState())
    val diaryMonthUIState: StateFlow<DiaryMonthUIState> = _diaryMonthUIState

    fun getDiaryByDate(date: LocalDate) {
        viewModelScope.launch {
            beenAloneRepository.getListDiaryByDate(date.dayOfMonth, date.monthValue, date.year)
                .collect {
                    _diaryDateUIState.value = DiaryDateUIState(it)
                }
        }
    }

    fun getDiaryByMonth(date: LocalDate) {
        viewModelScope.launch {
            beenAloneRepository.getListDiaryByMonth(date.monthValue, date.year)
                .collect {
                    _diaryMonthUIState.value = DiaryMonthUIState(it)
                }
        }
    }
}