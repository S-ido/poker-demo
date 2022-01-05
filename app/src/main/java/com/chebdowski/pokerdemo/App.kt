package com.chebdowski.pokerdemo

import android.app.Application
import com.chebdowski.pokerdemo.di.applicationModule
import com.chebdowski.pokerdemo.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(networkModule)
            modules(applicationModule)
        }
    }
}