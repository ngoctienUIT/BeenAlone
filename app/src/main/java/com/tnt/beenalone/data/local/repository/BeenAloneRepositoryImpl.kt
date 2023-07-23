package com.tnt.beenalone.data.local.repository

import com.tnt.beenalone.data.local.dao.UserDao
import com.tnt.beenalone.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class BeenAloneRepositoryImpl(private val userDao: UserDao) : BeenAloneRepository {
    override fun getUser(): Flow<UserEntity?> = userDao.getUser()

    override suspend fun upsertUser(item: UserEntity) = userDao.upsertUser(item)
}