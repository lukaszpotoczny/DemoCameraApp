package com.example.democameraapp.data.rest

import com.example.democameraapp.data.AppConst.SET_COOKIES_HEADER
import com.example.democameraapp.repository.SharedPrefsRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ReceivedCookiesInterceptor @Inject constructor(
    private val sharedPrefsRepository: SharedPrefsRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers(SET_COOKIES_HEADER).isNotEmpty()) {
            var cookies = ""
            for (header in originalResponse.headers(SET_COOKIES_HEADER)) {
                cookies += header.split(FIELD_SEPARATOR)[0] + FIELD_SEPARATOR
            }
            sharedPrefsRepository.setCookies(cookies)
        }
        return originalResponse
    }

    companion object {
        private const val FIELD_SEPARATOR = ";"
    }
}