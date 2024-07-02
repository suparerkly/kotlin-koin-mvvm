package com.example.composemultiplatform

import android.app.Application
import com.example.composemultiplatform.di.initKoin
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this@App)
    }
}