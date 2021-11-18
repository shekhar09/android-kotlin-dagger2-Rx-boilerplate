package com.shekhar.kotlin.ui.home

import androidx.lifecycle.MutableLiveData
import com.shekhar.kotlin.data.local.db.DatabaseService
import com.shekhar.kotlin.data.remote.NetworkService
import com.shekhar.kotlin.di.FragmentScope
import com.shekhar.kotlin.ui.base.BaseViewModel
import com.shekhar.kotlin.ui.home.post.Post
import com.shekhar.kotlin.utils.network.NetworkHelper
import com.shekhar.kotlin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
class HomeViewModel  (
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        private val databaseService: DatabaseService,
        private val networkService: NetworkService) : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper){



    val testData = MutableLiveData<List<Post>>()

    override fun onCreate() {



//    val someData: String
//        get() = (databaseService.dummyData
//                + " : " + networkService.dummyData
//                + " : " + networkHelper.isNetworkConnected)
}
}
