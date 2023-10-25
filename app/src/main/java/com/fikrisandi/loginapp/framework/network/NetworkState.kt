package com.fikrisandi.loginapp.framework.network

import java.lang.Exception

sealed class NetworkState<out T> {

    data class Success<out T>(
        val data: T,
        val code: Int,
    ) : NetworkState<T>()

    data class Failed(
        val exception: Exception
    ) : NetworkState<Nothing>()
}
