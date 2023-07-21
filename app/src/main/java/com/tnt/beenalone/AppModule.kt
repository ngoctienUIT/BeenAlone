package com.tnt.beenalone

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.tnt.beenalone.core.Constants
import com.tnt.beenalone.data.local.dao.UserDao
import com.tnt.beenalone.data.local.database.BeenAloneDatabase
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.data.local.repository.BeenAloneRepositoryImpl
import com.tnt.beenalone.data.local.store.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.BEEN_ALONE_DATASTORE)

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)

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
