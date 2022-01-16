package com.example.democameraapp.repository

import com.example.democameraapp.data.rest.ApiService
import com.example.democameraapp.data.request.AuthorizeRequest
import com.example.democameraapp.data.response.AuthorizeResponse
import com.example.democameraapp.data.request.LoginRequest
import com.example.democameraapp.data.response.LoginResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun signIn(username: String, password: String): Observable<LoginResponse> =
        apiService.signIn(LoginRequest(username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun authorize(token: String): Observable<AuthorizeResponse> =
        apiService.authorize(AuthorizeRequest(token))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}