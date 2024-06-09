package com.example.testrelog.di

import com.example.testrelog.BuildConfig
import com.example.testrelog.domain.data.remote.RelogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @RelogAppUrl
    fun provideDevMassAppUrl(): String{
        return BuildConfig.API_URL
    }

    @BasicOkHttpClient
    @Provides
    fun provideBasicOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @RelogAppUrl
    @Provides
    @Singleton
    fun getDevMassRetrofit(
        @RelogAppUrl url: String,
        @BasicOkHttpClient okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getDevMassApi(@RelogAppUrl retrofit: Retrofit): RelogApi {
        return retrofit.create(
            RelogApi::class.java
        )
    }

}

@Qualifier
annotation class RelogAppUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicOkHttpClient
