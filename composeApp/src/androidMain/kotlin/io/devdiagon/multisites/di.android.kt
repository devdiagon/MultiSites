package io.devdiagon.multisites

import io.devdiagon.multisites.data.AndroidRegionDataSource
import io.devdiagon.multisites.data.RegionDataSource
import io.devdiagon.multisites.data.database.getDatabaseBuilder
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    // Context is already declared when initializing Koin in Android application
    single { getDatabaseBuilder(get()) }
    factoryOf(::AndroidRegionDataSource) bind RegionDataSource::class
}