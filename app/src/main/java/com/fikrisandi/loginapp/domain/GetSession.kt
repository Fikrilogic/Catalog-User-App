package com.fikrisandi.loginapp.domain

import com.fikrisandi.loginapp.framework.base.usecase.BaseUseCase
import com.fikrisandi.loginapp.framework.network.fromJson
import com.fikrisandi.loginapp.model.user.Session
import com.fikrisandi.loginapp.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject
import javax.inject.Singleton

class GetSession @Inject constructor(private val repository: AuthenticationRepository) :
    BaseUseCase<Unit, Session?>() {
    override suspend fun FlowCollector<Session?>.execute(params: Unit) {
        try {
            val result = repository.getSession()
            if (result.isNotEmpty()) {
                emit(
                    result.fromJson<Session>()
                )
            } else {
                emit(null)
            }
        } catch (e: Exception) {
            emit(null)
        }
    }
}