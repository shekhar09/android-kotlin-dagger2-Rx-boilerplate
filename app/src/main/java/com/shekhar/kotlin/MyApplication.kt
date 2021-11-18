package com.shekhar.kotlin

import android.app.Application

import com.shekhar.kotlin.data.local.db.DatabaseService
import com.shekhar.kotlin.data.remote.NetworkService
import com.shekhar.kotlin.di.component.ApplicationComponent
import com.shekhar.kotlin.di.component.DaggerApplicationComponent
import com.shekhar.kotlin.di.module.ApplicationModule

import javax.inject.Inject

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var databaseService: DatabaseService

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }
}