package com.tnt.beenalone.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.tnt.beenalone.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from User WHERE id = 1")
    fun getUser(): Flow<UserEntity?>

    @Upsert
    suspend fun upsertUser(item: UserEntity)
}