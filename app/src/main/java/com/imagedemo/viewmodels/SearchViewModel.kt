package com.imagedemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imagedemo.models.SearchResponse
import com.imagedemo.repository.FlikrRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val flikrRepo: FlikrRepo) : BaseViewModel() {


    fun search(apiKey: String, query: String): LiveData<SearchResponse>? {

        val searchLiveData: MutableLiveData<SearchResponse> = MutableLiveData()

        mDisposable.add(
            flikrRepo.search(
                apiKey,
                query
            ).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let(searchLiveData::postValue)
                },
                    {
                        mError = true
                        searchLiveData.postValue(null)
                    })
        )

        return searchLiveData

    }



}