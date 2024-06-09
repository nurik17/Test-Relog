package com.example.testrelog.di

import com.example.testrelog.domain.data.local.AuthRepositoryImpl
import com.example.testrelog.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}