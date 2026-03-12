package com.godzuche.rickandmortydirectory.app

import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import com.godzuche.rickandmortydirectory.app.di.appModule
import com.godzuche.rickandmortydirectory.core.data.di.dataStoreModule
import com.godzuche.rickandmortydirectory.core.data.di.coroutineScopesModule
import com.godzuche.rickandmortydirectory.core.data.di.dataModule
import com.godzuche.rickandmortydirectory.core.data.di.databaseModule
import com.godzuche.rickandmortydirectory.core.data.di.dispatchersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyDirectoryApplication : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizeBytes(512L * 1024 * 1024) // Set a 512MB limit
                    .build()
            }
            // Optimized Network Fetcher (e.g., adding headers)
            .components {
                add(OkHttpNetworkFetcherFactory())
            }
            .build()
    }

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
