package com.imagedemo.repository

import com.imagedemo.models.SearchDetails
import com.imagedemo.models.SearchResponse
import io.reactivex.Observable

interface FlikrRepo {

    fun search(apiKey: String, query: String): Observable<SearchResponse>

    fun searchDetails(apiKey: String, photoId: String): Observable<SearchDetails>
}