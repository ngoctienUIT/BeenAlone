package com.tnt.beenalone.presentation.add_diary

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.entity.DiaryEntity
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddDiaryViewModel @Inject constructor(
    private val beenAloneRepository: BeenAloneRepository,
) : ViewModel() {
    private val _addDiaryUIState = MutableStateFlow(AddDiaryUIState())
    val addDiaryUIState: StateFlow<AddDiaryUIState> = _addDiaryUIState

    var selectedFeeling by mutableIntStateOf(-1)

    var content by mutableStateOf<String?>(null)

    var isVisibility by mutableStateOf(false)

    var isSuccess by mutableStateOf(false)

    var date: LocalDate by mutableStateOf(LocalDate.now())

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            beenAloneRepository.getUser().collect {
                Log.d("user", it.toString())
                if (it != null) {
                    _addDiaryUIState.value = AddDiaryUIState(it.toUser())
                }
            }
        }
    }

    fun getDiary(id: Long) {
        viewModelScope.launch {
            val diary = beenAloneRepository.getDiary(id).first()
            Log.d("diary", diary.toString())
            if (diary != null) {
                selectedFeeling = diary.feel
                content = diary.content
                date = LocalDate.of(diary.year, diary.month, diary.day)
            }
        }
    }

    fun saveDiary(date: LocalDate) {
        viewModelScope.launch {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val time = LocalTime.now()
            beenAloneRepository.upsertDiary(
                DiaryEntity(
                    selectedFeeling,
                    content,
                    date.dayOfMonth,
                    date.monthValue,
                    date.year,
                    formatter.format(time)
                )
            )
            isSuccess = true
        }
    }

    fun updateDiary(id: Long) {
        viewModelScope.launch {
            val diary = beenAloneRepository.getDiary(id).first()
            if (diary != null) {
                beenAloneRepository.upsertDiary(
                    diary.copy(feel = selectedFeeling, content = content)
                )
            }
            isSuccess = true
        }
    }

    fun deleteDiary(id: Long) {
        viewModelScope.launch {
            val diary = beenAloneRepository.getDiary(id).first()
            if (diary != null) {
                beenAloneRepository.deleteDiary(diary)
                isSuccess = true
            }
        }
    }
}