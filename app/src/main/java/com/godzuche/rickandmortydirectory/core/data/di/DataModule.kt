package com.godzuche.rickandmortydirectory.core.data.di

import com.godzuche.rickandmortydirectory.core.data.datastore.RickAndMortyPreferencesDataSource
import com.godzuche.rickandmortydirectory.core.data.repository.OfflineFirstUserDataRepository
import com.godzuche.rickandmortydirectory.core.domain.repository.UserDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    singleOf(::RickAndMortyPreferencesDataSource)
//    singleOf(::OfflineFirstUserDataRepository) { bind<UserDataRepository>() }
    single<UserDataRepository> {
        val ioDispatcher = get<CoroutineDispatcher>(named(DendDispatchers.IO))

        OfflineFirstUserDataRepository(
            preferencesDataSource = get(),
            ioDispatcher = ioDispatcher,
        )
    }

//    single<PhoneCallDataSource> {
//        val ioDispatcher = get<CoroutineDispatcher>(named(DendDispatchers.IO))
//        val scope = get<CoroutineScope>(named<ApplicationScope>())
//
//        PhoneCallDataSource(
//            context = androidContext(),
//            ioDispatcher = ioDispatcher,
//            scope = scope,
//        )
//    }

//    singleOf(::RulesRepositoryImpl).bind<RulesRepository>()
//
//    single { PhoneNumberNormalizer(context = androidContext()) }
//    singleOf(::ActivityRepositoryImpl).bind<ActivityRepository>()
}