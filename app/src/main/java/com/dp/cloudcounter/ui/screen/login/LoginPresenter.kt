package com.dp.cloudcounter.ui.screen.login

import android.util.Log
import com.dp.cloudcounter.data.storage.PreferenceStorage
import com.dp.cloudcounter.di.component.PresenterComponent
import com.dp.cloudcounter.domain.usecases.LoginInteractor
import com.dp.cloudcounter.ui.screen.core.BasePresenter
import com.dp.cloudcounter.ui.screen.core.BaseScreen
import javax.inject.Inject

class LoginPresenter(sourceScreen: LoginPresenter.Screen,
                     presenterComponent: PresenterComponent) : BasePresenter<LoginPresenter.Screen>(sourceScreen) {

    init {
        presenterComponent.inject(this)
    }

    @Inject
    lateinit var loginInteractor: LoginInteractor
    @Inject
    lateinit var prefs: PreferenceStorage

    fun login(rememberUser: Boolean, login: String, password: String) {
        prefs.rememberUser(rememberUser)
        loginInteractor.login(login, password, {
            if (it) {
                screen.successLogin()
            } else {
                Log.e(javaClass.simpleName, "Can\'t login with: $login, $password")
            }
        })
    }

    interface Screen : BaseScreen {

        fun successLogin()
    }
}