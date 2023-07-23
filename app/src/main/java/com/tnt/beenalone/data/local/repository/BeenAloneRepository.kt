package com.tnt.beenalone.data.local.repository

import com.tnt.beenalone.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface BeenAloneRepository {
    fun getUser(): Flow<UserEntity?>

    suspend fun upsertUser(item: UserEntity)
}