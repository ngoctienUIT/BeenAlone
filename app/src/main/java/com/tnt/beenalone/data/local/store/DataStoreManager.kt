package com.tnt.beenalone.data.local.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.tnt.beenalone.dataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    suspend fun setString(key: String, value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    fun getString(key: String) = context.dataStore.data.map {
        it[stringPreferencesKey(key)]
    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }
}