package com.shekhar.kotlin.data.remote.repository


import com.shekhar.kotlin.data.model.Dummy
import com.shekhar.kotlin.data.local.db.DatabaseService
import com.shekhar.kotlin.data.remote.NetworkService
import com.shekhar.kotlin.data.remote.request.DummyRequest
import io.reactivex.Single
import javax.inject.Inject

class DummyRepository @Inject constructor(
        private val networkService: NetworkService,
        private val databaseService: DatabaseService
) {

    fun fetchDummy(id: String): Single<List<Dummy>> =
        networkService.doDummyCall(DummyRequest(id)).map { it.data }

}