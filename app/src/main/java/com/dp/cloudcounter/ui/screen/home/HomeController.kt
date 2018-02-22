package com.dp.cloudcounter.ui.screen.home

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dp.cloudcounter.App
import com.dp.cloudcounter.R
import com.dp.cloudcounter.databinding.ControllerHomeBinding
import com.dp.cloudcounter.di.component.DaggerPresenterComponent
import com.dp.cloudcounter.di.component.PresenterComponent
import com.dp.cloudcounter.ui.screen.core.BaseController

class HomeController: BaseController<HomePresenter.Screen, HomePresenter>(), HomePresenter.Screen {

    private val component: PresenterComponent by lazy {
        DaggerPresenterComponent.builder()
                .appComponent(App.component)
                .build()
    }

    override fun createPresenter(): HomePresenter = HomePresenter(this, component)

    private lateinit var binding: ControllerHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.controller_home, container, false)
        return binding.root
    }
}