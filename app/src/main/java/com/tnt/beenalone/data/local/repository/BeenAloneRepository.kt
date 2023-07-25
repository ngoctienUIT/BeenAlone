package com.tnt.beenalone.data.local.repository

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.tnt.beenalone.data.local.entity.DiaryEntity
import com.tnt.beenalone.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface BeenAloneRepository {
    //User
    fun getUser(): Flow<UserEntity?>

    suspend fun upsertUser(item: UserEntity)

    //Diary
    fun getDiary(id: Long): Flow<DiaryEntity?>

    fun getAllDiary():Flow<List<DiaryEntity>>

    fun getListDiaryByDate(day: Int, month: Int, year: Int): Flow<List<DiaryEntity>>

    fun getListDiaryByMonth(month: Int, year: Int): Flow<List<DiaryEntity>>

    suspend fun upsertDiary(item: DiaryEntity)

    suspend fun deleteDiary(item: DiaryEntity)

    fun deleteDiaryById(id: Long)
}