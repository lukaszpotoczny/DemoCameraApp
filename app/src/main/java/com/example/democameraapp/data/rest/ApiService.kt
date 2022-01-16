package com.example.democameraapp.data.rest

import com.example.democameraapp.data.request.AuthorizeRequest
import com.example.democameraapp.data.request.LoginRequest
import com.example.democameraapp.data.response.AuthorizeResponse
import com.example.democameraapp.data.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/g/aaa/authenticate")
    fun signIn(@Body loginRequest: LoginRequest): Observable<LoginResponse>

    @POST("/g/aaa/authorize")
    fun authorize(@Body authorizeRequest: AuthorizeRequest): Observable<AuthorizeResponse>

    @GET("/g/device/list")
    fun getDeviceList(): Observable<List<List<Any>>>
}