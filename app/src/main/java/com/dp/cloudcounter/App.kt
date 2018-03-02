package com.dp.cloudcounter

import android.app.Application
import com.dp.cloudcounter.di.component.AppComponent
import com.dp.cloudcounter.di.component.DaggerAppComponent
import com.dp.cloudcounter.di.component.PresenterComponent
import com.dp.cloudcounter.di.module.AppModule

class App : Application() {

    companion object {
        @JvmStatic lateinit var component: AppComponent
        @JvmStatic lateinit var presenterComponent: PresenterComponent
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDI()
    }

    private fun initDI() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        component.application(this)

        presenterComponent = component.presenterComponent()
    }
}