package com.tnt.beenalone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tnt.beenalone.data.local.dao.DiaryDao
import com.tnt.beenalone.data.local.dao.UserDao
import com.tnt.beenalone.data.local.entity.DiaryEntity
import com.tnt.beenalone.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, DiaryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BeenAloneDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun diaryDao(): DiaryDao
}