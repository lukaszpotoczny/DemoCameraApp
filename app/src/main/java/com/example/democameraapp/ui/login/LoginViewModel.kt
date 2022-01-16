package com.example.democameraapp.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.democameraapp.repository.AuthenticationRepository
import com.example.democameraapp.base.BaseViewModel
import com.example.democameraapp.data.response.AuthorizeResponse
import com.example.democameraapp.data.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    private var token = ""

    val loginSuccessfulLiveData = MutableLiveData(false)


    fun signIn() {
        authenticationRepository.signIn(
            username = USERNAME_MOCK,
            password = PASSWORD_MOCK
        ).subscribe(object : Observer<LoginResponse> {
            override fun onSubscribe(disposable: Disposable) {
                disposableObservables.add(disposable)
            }

            override fun onNext(loginResponse: LoginResponse) {
                token = loginResponse.token
            }

            override fun onError(throwable: Throwable) {
                loginSuccessfulLiveData.value = false
            }

            override fun onComplete() {
                authorize()
            }
        })
    }

    private fun authorize() {
        if (token.isNotEmpty()) {
            authenticationRepository.authorize(token = token)
                .subscribe(object : Observer<AuthorizeResponse> {
                    override fun onSubscribe(disposable: Disposable) {
                        disposableObservables.add(disposable)
                    }

                    override fun onNext(authorizeResponse: AuthorizeResponse) {
                        // save user data
                    }

                    override fun onError(throwable: Throwable) {
                        loginSuccessfulLiveData.value = false
                    }

                    override fun onComplete() {
                        loginSuccessfulLiveData.value = true
                    }
                })
        }
    }

    companion object {
        private const val USERNAME_MOCK = "demo@een.com"
        private const val PASSWORD_MOCK = "cloudleader"
    }
}