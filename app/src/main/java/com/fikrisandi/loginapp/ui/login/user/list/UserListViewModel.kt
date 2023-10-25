package com.fikrisandi.loginapp.ui.login.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fikrisandi.loginapp.domain.GetUser
import com.fikrisandi.loginapp.framework.base.viewmodel.MvvmViewModel
import com.fikrisandi.loginapp.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val getUser: GetUser) : MvvmViewModel() {


    private val _userListPaging = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val userListPaging = _userListPaging.asStateFlow()

    private val paging = PagingConfig(pageSize = 20, initialLoadSize = 10, prefetchDistance = 10)


    init {
        getUser()
    }

    fun getUser() = safeLaunch {
        val params = GetUser.Params(
            config = paging
        )

        getUser(params)
            .onStart {
                startLoading()
            }
            .cachedIn(viewModelScope)
            .collectLatest {
                _userListPaging.emit(it)
            }
    }
}