package com.example.democameraapp.data.rest

import com.example.democameraapp.data.AppConst.COOKIES_HEADER
import com.example.democameraapp.repository.SharedPrefsRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CookiesInterceptor @Inject constructor(
    private val sharedPrefsRepository: SharedPrefsRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(COOKIES_HEADER, sharedPrefsRepository.getCookies())
                .build()
        )
    }
}