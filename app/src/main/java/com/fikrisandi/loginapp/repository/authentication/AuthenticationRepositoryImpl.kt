package com.fikrisandi.loginapp.repository.authentication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fikrisandi.loginapp.constant.Constant
import com.fikrisandi.loginapp.framework.network.AppHttpClient
import com.fikrisandi.loginapp.framework.network.NetworkState
import com.fikrisandi.loginapp.model.user.Session
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class AuthenticationRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val appHttpClient: AppHttpClient
) :
    AuthenticationRepository {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private object PreferencesKey {
        val sessionKey = stringPreferencesKey(name = "session")
    }

    override suspend fun login(email: String, password: String): NetworkState<Session> {
        return try {
            val response = appHttpClient.invoke().post("${Constant.BASE_URL}/api/login") {
                val data = mapOf(
                    "email" to email,
                    "password" to password
                )

                contentType(ContentType.Application.Json)
                setBody(data)
            }
            NetworkState.Success(
                data = response.body(),
                code = response.status.value
            )
        } catch (e: Exception) {
            NetworkState.Failed(e)
        }
    }

    override suspend fun setSession(session: String) {
        context.datastore.edit {
            it[PreferencesKey.sessionKey] = session
        }
    }

    override suspend fun getSession(): String {
        return context.datastore.data.map {
            it[PreferencesKey.sessionKey].orEmpty()
        }.firstOrNull().orEmpty()
    }
}