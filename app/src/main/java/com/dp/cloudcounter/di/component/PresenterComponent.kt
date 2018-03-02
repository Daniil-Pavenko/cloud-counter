package com.dp.cloudcounter.di.component

import com.dp.cloudcounter.di.annotation.PresenterScope
import com.dp.cloudcounter.di.module.InteractorModule
import com.dp.cloudcounter.di.module.PresenterMapperModule
import com.dp.cloudcounter.ui.screen.home.HomePresenter
import dagger.Subcomponent

@Subcomponent(modules = [
    (InteractorModule::class),
    (PresenterMapperModule::class)])
@PresenterScope
interface PresenterComponent {

    fun inject(homePresenter: HomePresenter)
}