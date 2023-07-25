package com.tnt.beenalone.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.tnt.beenalone.data.local.entity.DiaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("SELECT * from Diary WHERE id = :id")
    fun getDiary(id: Long): Flow<DiaryEntity?>

    @Query("SELECT * from Diary ORDER BY id ASC")
    fun getAllDiary(): Flow<List<DiaryEntity>>

    @Query("SELECT * from Diary WHERE day = :day AND month = :month AND year = :year")
    fun getListDiaryByDate(day: Int, month: Int, year: Int): Flow<List<DiaryEntity>>

    @Query("SELECT * from Diary WHERE month = :month AND year = :year")
    fun getListDiaryByMonth(month: Int, year: Int): Flow<List<DiaryEntity>>

    @Upsert
    suspend fun upsertDiary(item: DiaryEntity)

    @Delete
    suspend fun deleteDiary(item: DiaryEntity)

    @Query("DELETE FROM Diary WHERE id = :id")
    fun deleteDiaryById(id: Long)
}