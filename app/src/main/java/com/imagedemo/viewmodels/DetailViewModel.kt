package com.imagedemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imagedemo.models.SearchDetails
import com.imagedemo.repository.FlikrRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val flikrRepo: FlikrRepo) : BaseViewModel() {


    fun searchDetails(apiKey: String, photoId: String): LiveData<SearchDetails>? {

        val searchDetailsLiveData: MutableLiveData<SearchDetails> = MutableLiveData()

        mDisposable.add(
            flikrRepo.searchDetails(apiKey, photoId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchDetails ->
                    searchDetails?.let {
                        searchDetailsLiveData.postValue(it)
                    }


                }, {
                    mError = true
                    searchDetailsLiveData.postValue(null)

                })
        )

        return searchDetailsLiveData

    }


}