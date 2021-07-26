package com.app.loginsample.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class LoginViewModel : ViewModel() {

    val userName: MutableStateFlow<String?> = MutableStateFlow(null)
    val password: MutableStateFlow<String?> = MutableStateFlow(null)
    val enabled: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        handleInput()
    }

    private fun handleInput() {
        viewModelScope.launch {
            launch { userName.collect { enabled.emit(!(it.isNullOrBlank() || password.value.isNullOrBlank())) } }
            launch { password.collect { enabled.emit(!(it.isNullOrBlank() || password.value.isNullOrBlank())) } }
        }
    }

}