package com.fikrisandi.loginapp.repository.authentication

import com.fikrisandi.loginapp.framework.network.NetworkState
import com.fikrisandi.loginapp.model.user.Session

interface AuthenticationRepository {

    suspend fun login(email: String, password: String): NetworkState<Session>

    suspend fun setSession(session: String)
    suspend fun getSession(): String
}