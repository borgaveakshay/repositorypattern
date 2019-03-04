package com.imagedemo.api

import com.imagedemo.models.SearchDetails
import com.imagedemo.models.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FlikrApi {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    fun search(@Query("api_key") apiKey: String, @Query("tags") query: String): Observable<SearchResponse>

    @GET("?method=flickr.photos.getContext&format=json&nojsoncallback=1")
    fun searchDetail(@Query("api_key") apiKey: String, @Query("photo_id") photoId: String): Observable<SearchDetails>
}