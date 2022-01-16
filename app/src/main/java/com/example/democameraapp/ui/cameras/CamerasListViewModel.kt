package com.example.democameraapp.ui.cameras

import androidx.lifecycle.MutableLiveData
import com.example.democameraapp.repository.CameraRepository
import com.example.democameraapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

import com.example.democameraapp.data.model.Camera
import org.json.JSONObject

@HiltViewModel
class CamerasListViewModel @Inject constructor(
    private val cameraRepository: CameraRepository
) : BaseViewModel() {

    val cameraListLiveData = MutableLiveData<List<Camera>>()

    private val cameras = mutableListOf<Camera>()

    fun fetchDeviceList() {
        cameraRepository.getDeviceList()
            .subscribe(object : Observer<List<List<Any>>> {
                override fun onSubscribe(disposable: Disposable) {
                    disposableObservables.add(disposable)
                }

                override fun onNext(list: List<List<Any>>) {
                    list.forEach { response ->
                        mapToCamera(response)
                    }
                }

                override fun onError(throwable: Throwable) {
                    // handle error
                }

                override fun onComplete() {
                    cameraListLiveData.value = cameras
                }
            })
    }

    private fun mapToCamera(response: List<Any>) {
        val cam = Camera(
            response[0] as? String,
            response[1] as? String,
            response[2] as? String,
            response[3] as? String,
            response[4] as? List<List<String>>,
            response[5] as? String,
            response[6] as? String,
            response[7] as? List<String>,
            response[8] as? String,
            response[9] as? String,
            response[10] as? Int,
            response[11] as? String,
            response[12] as? Int,
            response[13] as? Int,
            response[14] as? String,
            response[15] as? Int,
            response[16] as? String,
            response[17] as? Boolean,
            response[18] as? String,
            response[19] as? String,
            response[20] as? List<Any>,
            response[21] as? String,
            response[22] as? String,
            response[23] as? Int,
            response[24] as? List<String>,
            response[25] as? Int,
            response[26] as? JSONObject,
            response[27] as? JSONObject,
            response[28] as? JSONObject,
        )
        cameras.add(cam)
    }
}