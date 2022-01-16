package com.example.democameraapp.di

import com.example.democameraapp.data.AppConst.API_TIMEOUT
import com.example.democameraapp.data.AppConst.BASE_URL
import com.example.democameraapp.data.rest.CookiesInterceptor
import com.example.democameraapp.data.rest.ReceivedCookiesInterceptor
import com.example.democameraapp.repository.SharedPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCookiesInterceptor(
        sharedPrefsRepository: SharedPrefsRepository
    ): CookiesInterceptor = CookiesInterceptor(sharedPrefsRepository)

    @Singleton
    @Provides
    fun provideReceivedCookiesInterceptor(
        sharedPrefsRepository: SharedPrefsRepository
    ): ReceivedCookiesInterceptor = ReceivedCookiesInterceptor(sharedPrefsRepository)

    @Singleton
    @Provides
    fun createOkHttpClient(
        cookiesInterceptor: CookiesInterceptor,
        receivedCookiesInterceptor: ReceivedCookiesInterceptor
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(cookiesInterceptor)
            .addInterceptor(receivedCookiesInterceptor)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    @Named("retrofit")
    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}