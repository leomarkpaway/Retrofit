package com.leomarkpaway.retrofit

import androidx.activity.viewModels
import com.leomarkpaway.android_basics.common.base.BaseActivity
import com.leomarkpaway.retrofit.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        TODO("Not yet implemented")
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }
}