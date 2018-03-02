package com.dp.cloudcounter.data.storage

interface PreferenceStorage {

    fun rememberUser(remember: Boolean)

    fun isRememberedUser(): Boolean
}