package com.shekhar.kotlin.data.remote

import com.shekhar.kotlin.data.remote.request.DummyRequest
import com.shekhar.kotlin.data.remote.request.LoginRequest
import com.shekhar.kotlin.data.remote.request.SignupRequest
import com.shekhar.kotlin.data.remote.response.DummyResponse
import com.shekhar.kotlin.data.remote.response.LoginResponse
import com.shekhar.kotlin.data.remote.response.SignupResponse

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

import javax.inject.Singleton


@Singleton
interface NetworkService {


    @POST(Endpoints.LOGIN)
    fun doLoginCall(
            @Body request: LoginRequest
    ): Single<LoginResponse>


    @POST(Endpoints.SIGNUP)
    fun doSignupCall(
            @Body request: SignupRequest
    ): Single<SignupResponse>


    @POST(Endpoints.DUMMY)
    fun doDummyCall(
            @Body request: DummyRequest,
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
    ): Single<DummyResponse>
}
