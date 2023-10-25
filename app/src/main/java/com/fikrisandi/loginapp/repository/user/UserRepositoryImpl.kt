package com.fikrisandi.loginapp.repository.user

import com.fikrisandi.loginapp.constant.Constant
import com.fikrisandi.loginapp.framework.network.AppHttpClient
import com.fikrisandi.loginapp.framework.network.NetworkState
import com.fikrisandi.loginapp.model.user.PagingUser
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import javax.inject.Inject
import javax.inject.Singleton

class UserRepositoryImpl @Inject constructor(private val httpClient: AppHttpClient) :
    UserRepository {
    override suspend fun getUser(page: Int): NetworkState<PagingUser> {
        return try {
            val response = httpClient().get("${Constant.BASE_URL}/api/users?page=$page") {

            }
            NetworkState.Success(
                data = response.body(),
                code = response.status.value
            )
        } catch (e: Exception) {
            NetworkState.Failed(e)
        }
    }
}