package com.dp.cloudcounter.domain.impl

import android.content.Context
import com.dp.cloudcounter.domain.NetworkHelper

class NetworkHelperImpl(private val context: Context) : NetworkHelper {

    override fun hasInternet(): Boolean {
        return true
    }
}