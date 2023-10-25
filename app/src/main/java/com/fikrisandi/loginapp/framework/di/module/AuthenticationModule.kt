package com.fikrisandi.loginapp.framework.di.module

import android.content.Context
import com.fikrisandi.loginapp.framework.network.AppHttpClient
import com.fikrisandi.loginapp.repository.authentication.AuthenticationRepository
import com.fikrisandi.loginapp.repository.authentication.AuthenticationRepositoryImpl
import com.fikrisandi.loginapp.repository.user.UserRepository
import com.fikrisandi.loginapp.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        @ApplicationContext context: Context,
        appHttpClient: AppHttpClient
    ): AuthenticationRepository = AuthenticationRepositoryImpl(context, appHttpClient)

    @Provides
    @Singleton
    fun provideUserRepository(
        @ApplicationContext context: Context,
        appHttpClient: AppHttpClient
    ): UserRepository = UserRepositoryImpl(appHttpClient)
}