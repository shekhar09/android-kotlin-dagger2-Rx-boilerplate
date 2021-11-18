package com.shekhar.kotlin.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import ccom.shekhar.kotlin.dagger.data.remote.repository.UserRepository
import com.shekhar.kotlin.data.local.db.DatabaseService
import com.shekhar.kotlin.data.remote.NetworkService

import com.shekhar.kotlin.di.ActivityContext
import com.shekhar.kotlin.ui.base.BaseActivity
import com.shekhar.kotlin.ui.login.LoginViewModel
import com.shekhar.kotlin.ui.main.MainViewModel
import com.shekhar.kotlin.ui.signup.SignupViewModel
import com.shekhar.kotlin.utils.ViewModelProviderFactory
import com.shekhar.kotlin.utils.network.NetworkHelper
import com.shekhar.kotlin.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity



    @Provides
    fun provideMainViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            databaseService: DatabaseService,
            networkService: NetworkService
    ) :MainViewModel =
            ViewModelProviders.of(activity,
                    ViewModelProviderFactory(MainViewModel::class){
                        MainViewModel(schedulerProvider,compositeDisposable,networkHelper,databaseService,networkService)

            }).get(MainViewModel::class.java)


    @Provides
    fun provideLoginViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository/*,
            tokenRepository: TokenRepository*/
    ): LoginViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(LoginViewModel::class) {
        LoginViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository/*,tokenRepository*/)
    }).get(LoginViewModel::class.java)


    @Provides
    fun provideSignupViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository/*,
            tokenRepository: TokenRepository*/
    ): SignupViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(SignupViewModel::class) {
        SignupViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository/*,tokenRepository*/)
    }).get(SignupViewModel::class.java)

}
