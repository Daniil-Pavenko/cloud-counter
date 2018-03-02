package com.dp.cloudcounter.data.storage

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "cc_prefs"
private const val REMEMBER_USER = "REMEMBER_USER"

class PreferenceStorageImpl(private val context: Context) : PreferenceStorage {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun rememberUser(remember: Boolean) {
        prefs.edit().putBoolean(REMEMBER_USER, remember).apply()
    }

    override fun isRememberedUser(): Boolean = prefs.getBoolean(REMEMBER_USER, false)
}