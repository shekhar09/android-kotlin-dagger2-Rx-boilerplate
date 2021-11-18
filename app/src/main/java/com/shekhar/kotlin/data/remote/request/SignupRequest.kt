package com.shekhar.kotlin.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignupRequest (

        @Expose
        @SerializedName("email")
        var email: String,

        @Expose
        @SerializedName("password")
        var password: String,

        @Expose
        @SerializedName("name")
        var name: String
)