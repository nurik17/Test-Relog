package com.example.testrelog.utils

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constant {
    const val AUTH_PREFERENCES = "auth_pref"
    val AUTH_KEY = stringSetPreferencesKey("auth_key")

}