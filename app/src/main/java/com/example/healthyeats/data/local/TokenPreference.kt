package com.example.healthyeats.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenPreference @Inject constructor(private val dataStore: DataStore<Preferences>) {
    fun getToken(): Flow<String?>{
        return dataStore.data.map {
            it[TOKEN]
        }
    }

    suspend fun saveToken(token: String){
        dataStore.edit {
            it[TOKEN] = token
        }
    }

    companion object {
        private val TOKEN = stringPreferencesKey("token")
    }
}