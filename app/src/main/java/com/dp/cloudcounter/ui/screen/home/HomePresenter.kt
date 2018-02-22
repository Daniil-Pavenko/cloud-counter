package com.dp.cloudcounter.ui.screen.home

import com.dp.cloudcounter.di.component.PresenterComponent
import com.dp.cloudcounter.ui.screen.core.BasePresenter
import com.dp.cloudcounter.ui.screen.core.BaseScreen

class HomePresenter(ui: HomePresenter.Screen, component: PresenterComponent) : BasePresenter<HomePresenter.Screen>(ui) {

    init {
        component.inject(this)
    }

    override fun onCreate() {
        super.onCreate()
    }

    interface Screen : BaseScreen
}