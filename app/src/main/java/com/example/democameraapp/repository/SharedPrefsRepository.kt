package com.example.democameraapp.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.democameraapp.data.AppConst.COOKIES_KEY
import com.example.democameraapp.data.AppConst.SHARED_PREFS_KEY
import javax.inject.Inject

class SharedPrefsRepository @Inject constructor(
    private val context: Context
) {
    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            SHARED_PREFS_KEY,
            Context.MODE_PRIVATE
        )
    }

    fun getCookies(): String = prefs.value.getString(COOKIES_KEY, "") ?: ""

    fun setCookies(cookies: String) {
        prefs.value.edit().apply {
            putString(COOKIES_KEY, cookies)
            apply()
        }
    }
}