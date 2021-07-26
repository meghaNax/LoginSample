package com.app.loginsample.ui.login

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.app.loginsample.R
import com.app.loginsample.databinding.ActivityLoginBinding
import com.app.loginsample.ui.core.BaseActivity
import com.app.loginsample.ui.home.HomeActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login),
    View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun bindViewModel() {
        super.bindViewModel()
        screenBinding.viewModel = loginViewModel
    }

    override fun bindUIListeners() {
        super.bindUIListeners()
        screenBinding.clickListener = this
    }

    override fun onClick(view: View?) {
        view ?: return
        when (view.id) {
            R.id.btnLogin -> openHomeScreen()
        }
    }

    private fun openHomeScreen() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
    }
}