package com.fikrisandi.loginapp.repository.user

import com.fikrisandi.loginapp.framework.network.NetworkState
import com.fikrisandi.loginapp.model.user.PagingUser

interface UserRepository {

    suspend fun getUser(page: Int): NetworkState<PagingUser>
}