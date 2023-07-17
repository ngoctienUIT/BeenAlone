package com.tnt.beenalone

import android.content.Context
import androidx.room.Room
import com.tnt.beenalone.data.local.dao.UserDao
import com.tnt.beenalone.data.local.database.BeenAloneDatabase
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.data.local.repository.BeenAloneRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideBeenAloneDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BeenAloneDatabase::class.java, "BeenAloneDatabase")
            .build()

    @Provides
    fun provideUserDao(beenAloneDatabase: BeenAloneDatabase) = beenAloneDatabase.userDao()

    @Provides
    fun provideInventoryRepository(userDao: UserDao): BeenAloneRepository =
        BeenAloneRepositoryImpl(userDao)
}
