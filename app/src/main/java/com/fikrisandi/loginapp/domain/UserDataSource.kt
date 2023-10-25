package com.fikrisandi.loginapp.domain

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fikrisandi.loginapp.framework.network.NetworkState
import com.fikrisandi.loginapp.model.user.User
import com.fikrisandi.loginapp.repository.user.UserRepository
import javax.inject.Inject

class UserDataSource constructor(private val repository: UserRepository) :
    PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        return try {
            when (val response = repository.getUser(page)) {
                is NetworkState.Success -> {
                    Log.e(this::class.java.name, "load: ${response.data.data.orEmpty()}", )
                    LoadResult.Page(
                        data = response.data.data.orEmpty(),
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (response.data.data?.isEmpty() == true) null else page + 1
                    )
                }

                is NetworkState.Failed -> {
                    LoadResult.Error(response.exception)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}