package com.danieloliveira.clarochallenge

import android.app.Application
import com.danieloliveira.clarochallenge.di.appModule
import com.danieloliveira.clarochallenge.di.viewModelModule
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        JodaTimeAndroid.init(this)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(appModule, viewModelModule))
        }
    }
}