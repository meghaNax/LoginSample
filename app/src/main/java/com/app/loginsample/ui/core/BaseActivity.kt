package com.app.loginsample.ui.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<ViewDatBinding : ViewDataBinding>(@LayoutRes layout: Int) :
    AppCompatActivity() {

    protected val screenBinding: ViewDatBinding by lazy {
        DataBindingUtil.setContentView(this, layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(screenBinding.root)
        bindViewModel()
        bindUIListeners()
        bindLifeCycleOwner()
    }

    protected open fun bindUIListeners() {}

    protected open fun bindViewModel() {}

    private fun bindLifeCycleOwner() {
        screenBinding.lifecycleOwner = this
    }
}