package com.fikrisandi.loginapp.model.user


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Session(
    @SerializedName("token")
    @Expose
    var token: String = ""
)