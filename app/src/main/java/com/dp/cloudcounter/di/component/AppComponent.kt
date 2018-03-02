package com.dp.cloudcounter.di.component

import com.dp.cloudcounter.App
import com.dp.cloudcounter.di.module.AppModule
import com.dp.cloudcounter.di.module.MapperModule
import com.dp.cloudcounter.di.module.NetworkModule
import com.dp.cloudcounter.di.module.RepositoryModule
import com.dp.cloudcounter.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (MapperModule::class),
    (NetworkModule::class),
    (RepositoryModule::class)])
interface AppComponent {

    fun application(app: App)

    fun presenterComponent(): PresenterComponent

    fun inject(mainActivity: MainActivity)
}