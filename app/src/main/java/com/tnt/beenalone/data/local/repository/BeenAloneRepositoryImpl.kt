package com.tnt.beenalone.data.local.repository

import com.tnt.beenalone.data.local.dao.DiaryDao
import com.tnt.beenalone.data.local.dao.UserDao
import com.tnt.beenalone.data.local.entity.DiaryEntity
import com.tnt.beenalone.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class BeenAloneRepositoryImpl(private val userDao: UserDao, private val diaryDao: DiaryDao) :
    BeenAloneRepository {
    override fun getUser(): Flow<UserEntity?> = userDao.getUser()

    override suspend fun upsertUser(item: UserEntity) = userDao.upsertUser(item)

    override fun getDiary(id: Long): Flow<DiaryEntity?> = diaryDao.getDiary(id)

    override fun getAllDiary(): Flow<List<DiaryEntity>> = diaryDao.getAllDiary()

    override fun getListDiaryByDate(day: Int, month: Int, year: Int): Flow<List<DiaryEntity>> =
        diaryDao.getListDiaryByDate(day, month, year)

    override fun getListDiaryByMonth(month: Int, year: Int): Flow<List<DiaryEntity>> =
        diaryDao.getListDiaryByMonth(month, year)

    override suspend fun upsertDiary(item: DiaryEntity) = diaryDao.upsertDiary(item)

    override suspend fun deleteDiary(item: DiaryEntity) = diaryDao.deleteDiary(item)

    override fun deleteDiaryById(id: Long) = diaryDao.deleteDiaryById(id)
}