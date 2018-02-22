package com.dp.cloudcounter.ui.screen.core

import android.content.Context
import android.view.View
import com.bluelinelabs.conductor.Controller

abstract class BaseController<out S : BaseScreen, P : BasePresenter<S>> : Controller() {

    abstract fun createPresenter(): P
    lateinit var presenter: P

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        presenter = createPresenter()
        presenter.context = context
        presenter.resources = context.resources
        presenter.onCreate()
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.onAttach()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}