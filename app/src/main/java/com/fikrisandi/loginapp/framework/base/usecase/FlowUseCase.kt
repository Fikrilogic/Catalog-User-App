package com.fikrisandi.loginapp.framework.base.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


abstract class FlowUseCase<in Params, ReturnType> where ReturnType : Any {

    protected abstract fun execute(params: Params): Flow<ReturnType>

    operator fun invoke(params: Params): Flow<ReturnType> = execute(params)
        .flowOn(Dispatchers.IO)
}