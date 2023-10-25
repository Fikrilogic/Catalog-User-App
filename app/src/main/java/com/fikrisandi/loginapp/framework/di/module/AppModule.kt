package com.fikrisandi.loginapp.framework.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AuthenticationModule::class])
@InstallIn(SingletonComponent::class)
class AppModule