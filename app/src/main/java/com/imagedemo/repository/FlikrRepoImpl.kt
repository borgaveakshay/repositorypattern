package com.imagedemo.repository

import com.imagedemo.api.FlikrApi
import com.imagedemo.models.SearchDetails
import com.imagedemo.models.SearchResponse
import io.reactivex.Observable

class FlikrRepoImpl(private val flikrApi: FlikrApi) : FlikrRepo {

    override fun search(apiKey: String, query: String): Observable<SearchResponse> = flikrApi.search(apiKey, query)


    override fun searchDetails(apiKey: String, photoId: String): Observable<SearchDetails> =
        flikrApi.searchDetail(apiKey, photoId)

}