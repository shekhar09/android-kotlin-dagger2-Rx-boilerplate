package com.shekhar.kotlin.di.component

import com.shekhar.kotlin.di.module.FragmentModule
import com.shekhar.kotlin.di.FragmentScope
import com.shekhar.kotlin.ui.home.HomeFragment

import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
}
