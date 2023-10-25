package com.fikrisandi.loginapp.domain

import android.net.NetworkRequest
import com.fikrisandi.loginapp.framework.base.usecase.BaseUseCase
import com.fikrisandi.loginapp.framework.network.NetworkState
import com.fikrisandi.loginapp.framework.network.toJson
import com.fikrisandi.loginapp.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject
import javax.inject.Singleton

class SignInUser @Inject constructor(val repository: AuthenticationRepository) :
    BaseUseCase<SignInUser.Params, NetworkState<Unit>>() {
    data class Params(
        val email: String,
        val password: String
    )

    override suspend fun FlowCollector<NetworkState<Unit>>.execute(params: Params) {
        try {
            when (val result = repository.login(params.email.trim(), params.password.trim())) {
                is NetworkState.Success -> {
                    repository.setSession(result.data.toJson())
                    emit(NetworkState.Success(Unit, code = result.code))
                }

                is NetworkState.Failed -> {
                    emit(NetworkState.Failed(result.exception))
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}