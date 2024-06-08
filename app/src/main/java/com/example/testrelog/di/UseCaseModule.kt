package com.example.testrelog.di

import com.example.testrelog.domain.repository.AuthRepository
import com.example.testrelog.domain.useCase.LoginUseCase
import com.example.testrelog.domain.useCase.LoginUseCaseImpl
import com.example.testrelog.domain.useCase.RegisterUseCase
import com.example.testrelog.domain.useCase.RegisterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCaseImpl(repository)
    }
}