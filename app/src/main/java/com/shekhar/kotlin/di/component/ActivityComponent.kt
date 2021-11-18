package com.shekhar.kotlin.di.component

import com.shekhar.kotlin.di.module.ActivityModule
import com.shekhar.kotlin.di.ActivityScope
import com.shekhar.kotlin.ui.login.LoginActivity
import com.shekhar.kotlin.ui.main.MainActivity
import com.shekhar.kotlin.ui.signup.SignupActivity

import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: SignupActivity)
}
