package com.imagedemo.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.imagedemo.BuildConfig.BASE_URL
import com.imagedemo.R
import com.imagedemo.api.FlikrApi
import com.imagedemo.repository.FlikrRepo
import com.imagedemo.repository.FlikrRepoImpl
import com.imagedemo.viewmodels.viewmodelfactory.SearchViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class AppModule(private val context: Context) {


    fun getAppModule() = module {


        single("okhttp_log_interceptor") {
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(logging).build()
        }

        single("retrofit") {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get("okhttp_log_interceptor"))
                .build()

        }

        single("FlikrApi") {

            val retrofit = get<Retrofit>("retrofit")

            retrofit.create(FlikrApi::class.java)

        }

        single<FlikrRepo>("FlikrRepo") {
            FlikrRepoImpl(get("FlikrApi"))
        }

        single {

            SearchViewModelFactory(get("FlikrRepo"))
        }


    }
}