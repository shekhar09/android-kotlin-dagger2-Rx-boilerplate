package com.shekhar.kotlin.ui.main

import android.os.Bundle

import com.shekhar.kotlin.dagger.R
import com.shekhar.kotlin.ui.home.HomeFragment

import androidx.lifecycle.Observer
import com.shekhar.kotlin.di.component.ActivityComponent
import com.shekhar.kotlin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_main


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun setupView(saveInstanceId: Bundle?) {

        addHomeFragment()

    }


    override fun setupObserver() {
        super.setupObserver()

        viewModel.users.observe(this, Observer {
            tv_message.text = it.toString()
        })
    }


    private fun addHomeFragment() {
        if (supportFragmentManager.findFragmentByTag(HomeFragment.TAG) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container_fragment, HomeFragment.newInstance(), HomeFragment.TAG)
                    .commit()
        }
    }



    override fun onStart() {
        super.onStart()
        viewModel.getAllUsers()
    }

    override fun onStop() {
        super.onStop()
        viewModel.deleteUser()
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }


}
