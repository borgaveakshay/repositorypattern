package com.imagedemo

import android.app.Application
import com.imagedemo.di.AppModule
import org.koin.android.ext.android.startKoin

class MainApp : Application() {


    override fun onCreate() {
        super.onCreate()
        val appModule = AppModule(this)
        startKoin(this, listOf(appModule.getAppModule()))

    }
}