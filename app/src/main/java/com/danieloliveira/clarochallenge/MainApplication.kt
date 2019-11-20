package com.danieloliveira.clarochallenge

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.danieloliveira.clarochallenge.di.appModule
import com.danieloliveira.clarochallenge.di.viewModelModule
import com.danieloliveira.clarochallenge.source.local.MovieDataBase
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class MainApplication: Application() {

    companion object {
        var database: MovieDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        JodaTimeAndroid.init(this)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(appModule, viewModelModule))
        }

        database = Room.databaseBuilder(
            this,
            MovieDataBase::class.java,
            BuildConfig.MOVIE_DATABASE).build()
    }

    override fun onTerminate() {
        database?.close()
        super.onTerminate()
    }

}