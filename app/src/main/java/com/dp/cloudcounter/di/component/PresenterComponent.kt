package com.dp.cloudcounter.di.component

import com.dp.cloudcounter.di.annotation.PresenterScope
import com.dp.cloudcounter.ui.screen.home.HomePresenter
import dagger.Component

@Component(dependencies = [(AppComponent::class)])
@PresenterScope
interface PresenterComponent {

    fun inject(homePresenter: HomePresenter)
}