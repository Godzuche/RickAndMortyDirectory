package com.godzuche.rickandmortydirectory.app

import android.app.Application
import com.godzuche.rickandmortydirectory.app.di.appModule
import com.godzuche.rickandmortydirectory.core.data.di.dataStoreModule
import com.godzuche.rickandmortydirectory.core.data.di.coroutineScopesModule
import com.godzuche.rickandmortydirectory.core.data.di.dataModule
import com.godzuche.rickandmortydirectory.core.data.di.databaseModule
import com.godzuche.rickandmortydirectory.core.data.di.dispatchersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyDirectoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RickAndMortyDirectoryApplication)
            modules(
                appModule,
//                dataModule,
//                databaseModule,
//                dispatchersModule,
//                dataStoreModule,
//                coroutineScopesModule,
            )
        }
    }
}
