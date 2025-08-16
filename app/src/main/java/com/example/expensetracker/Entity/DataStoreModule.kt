package com.example.expensetracker.Entity

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

suspend fun Context.saveAuthData(accessToken: String, refreshToken: String, username: String) {
    dataStore.edit { prefs ->
        prefs[AuthResponse.ACCESS_TOKEN] = accessToken
        prefs[AuthResponse.REFRESH_TOKEN] = refreshToken
        prefs[AuthResponse.USER_NAME] = username
    }
}
fun Context.getAccessToken(): Flow<String?> =
    dataStore.data.map { it[AuthResponse.ACCESS_TOKEN] }

fun Context.getRefreshToken(): Flow<String?> =
    dataStore.data.map { it[AuthResponse.REFRESH_TOKEN] }

fun Context.getUsername(): Flow<String?> =
    dataStore.data.map { it[AuthResponse.USER_NAME] }

suspend fun Context.getAuthData(): Triple<String?, String?, String?> {
    val prefs = dataStore.data.first()
    return Triple(
        prefs[AuthResponse.ACCESS_TOKEN],
        prefs[AuthResponse.REFRESH_TOKEN],
        prefs[AuthResponse.USER_NAME]
    )
}



class DataStoreModule {
}