package com.example.democameraapp.di

import android.content.Context
import com.example.democameraapp.data.rest.ApiService
import com.example.democameraapp.repository.AuthenticationRepository
import com.example.democameraapp.repository.CameraRepository
import com.example.democameraapp.repository.SharedPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherApi(@Named("retrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesAuthenticationRepository(api: ApiService): AuthenticationRepository {
        return AuthenticationRepository(api)
    }

    @Singleton
    @Provides
    fun providesCameraRepository(api: ApiService): CameraRepository {
        return CameraRepository(api)
    }

    @Singleton
    @Provides
    fun providesSharedPrefsRepository(@ApplicationContext context: Context): SharedPrefsRepository {
        return SharedPrefsRepository(context)
    }
}