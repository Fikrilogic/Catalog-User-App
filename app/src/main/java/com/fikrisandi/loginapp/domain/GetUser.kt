package com.fikrisandi.loginapp.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fikrisandi.loginapp.framework.base.usecase.FlowUseCase
import com.fikrisandi.loginapp.model.user.User
import com.fikrisandi.loginapp.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class GetUser @Inject constructor(private val repository: UserRepository) :
    FlowUseCase<GetUser.Params, PagingData<User>>() {
    data class Params(
        val config: PagingConfig
    )

    override fun execute(params: Params): Flow<PagingData<User>> {
        return Pager(
            config = params.config,
            pagingSourceFactory = {
                UserDataSource(repository)
            }
        ).flow
    }
}