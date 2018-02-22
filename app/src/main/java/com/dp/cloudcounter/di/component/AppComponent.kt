package com.dp.cloudcounter.di.component

import com.dp.cloudcounter.App
import com.dp.cloudcounter.di.module.*
import com.dp.cloudcounter.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (InteractorModule::class), (MapperModule::class), (NetworkModule::class), (RepositoryModule::class), (StorageModule::class)])
interface AppComponent {

    fun application(app: App)

    fun inject(mainActivity: MainActivity)
}