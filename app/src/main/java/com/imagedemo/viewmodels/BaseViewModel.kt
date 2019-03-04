package com.imagedemo.viewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val mDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    var mError: Boolean = false


    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

}