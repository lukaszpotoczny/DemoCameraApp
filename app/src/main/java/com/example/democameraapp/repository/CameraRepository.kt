package com.example.democameraapp.repository

import com.example.democameraapp.data.rest.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CameraRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getDeviceList(): Observable<List<List<Any>>> =
        apiService.getDeviceList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}