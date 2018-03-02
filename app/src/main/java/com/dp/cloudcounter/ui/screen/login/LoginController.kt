package com.dp.cloudcounter.ui.screen.login

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dp.cloudcounter.App
import com.dp.cloudcounter.R
import com.dp.cloudcounter.databinding.ControllerLoginBinding
import com.dp.cloudcounter.ui.screen.core.BaseController

class LoginController : BaseController<LoginPresenter.Screen, LoginPresenter>(), LoginPresenter.Screen {

    override fun createPresenter(): LoginPresenter = LoginPresenter(this, App.presenterComponent)

    private lateinit var binding: ControllerLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.controller_login, container, false)
        return binding.root
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        binding.setOnSignInUpListener {
            presenter.login(
                    binding.cbRememberAccount.isChecked,
                    binding.etLogin.text.toString(),
                    binding.etPassword.text.toString()
            )
        }
    }
}