package com.example.mytmdb.app

import android.app.Application
import com.example.mytmdb.di.allModule
import com.example.mytmdb.di.remoteDataSourceModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App:Application(){
    override fun onCreate(){
        super.onCreate()

        initKoin()
        initTimber()

    }
    private fun initKoin(){
       startKoin{
           androidLogger(Level.ERROR)
           androidContext(this@App)
           modules(remoteDataSourceModule, allModule)
       }
    }

    private fun initTimber(){
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}