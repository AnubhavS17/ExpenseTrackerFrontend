package com.example.expensetracker.Entity

import androidx.datastore.preferences.core.stringPreferencesKey

object AuthResponse {
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("token")
    val USER_NAME = stringPreferencesKey("username")
}