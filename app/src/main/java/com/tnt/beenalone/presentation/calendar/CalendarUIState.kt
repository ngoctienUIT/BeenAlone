package com.tnt.beenalone.presentation.calendar

import com.tnt.beenalone.data.local.entity.DiaryEntity

data class DiaryDateUIState(val listDiary: List<DiaryEntity> = listOf())

data class DiaryMonthUIState(val listDiary: List<DiaryEntity> = listOf())