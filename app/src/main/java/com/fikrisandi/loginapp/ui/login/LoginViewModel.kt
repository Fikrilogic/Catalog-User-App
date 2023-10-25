package com.fikrisandi.loginapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fikrisandi.loginapp.domain.GetSession
import com.fikrisandi.loginapp.domain.SignInUser
import com.fikrisandi.loginapp.framework.base.viewmodel.MvvmViewModel
import com.fikrisandi.loginapp.framework.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUser: SignInUser,
    private val getSession: GetSession
) : MvvmViewModel() {


    private val _loginStatus = MutableStateFlow(false)
    val loginStatus = _loginStatus.asStateFlow()

    init {
        checkSession()
    }

    fun signIn(email: String, password: String, callback: (Boolean) -> Unit) = safeLaunch {
        val params = SignInUser.Params(
            email = email,
            password = password
        )

        signInUser(params)
            .onStart {
                startLoading()
            }
            .catch {
                callback.invoke(false)
            }
            .collectLatest {
                when (it) {
                    is NetworkState.Success -> {
                        if(it.code == HttpStatusCode.OK.value){
                            callback.invoke(true)
                        } else {
                            callback.invoke(false)
                        }
                    }

                    is NetworkState.Failed -> {
                        callback.invoke(false)
                    }
                }
            }
    }

    fun checkSession() = safeLaunch {
        getSession(Unit)
            .collectLatest {
                _loginStatus.emit(it != null)
            }
    }


}