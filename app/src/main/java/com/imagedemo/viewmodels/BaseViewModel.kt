package com.imagedemo.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val mDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    var mError: Boolean = false


    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

}