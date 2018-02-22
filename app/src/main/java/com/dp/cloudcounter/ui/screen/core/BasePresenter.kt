package com.dp.cloudcounter.ui.screen.core

import android.content.Context
import android.content.res.Resources

abstract class BasePresenter<out S : BaseScreen>(protected val screen: S) {

    lateinit var context: Context
    lateinit var resources: Resources

    open fun onCreate() {
    }

    open fun onAttach() {
    }

    open fun onDetach() {
    }

    open fun onDestroy() {
    }
}