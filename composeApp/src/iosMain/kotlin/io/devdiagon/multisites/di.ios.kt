package io.devdiagon.multisites

import io.devdiagon.multisites.data.IosRegionDataSource
import io.devdiagon.multisites.data.RegionDataSource
import io.devdiagon.multisites.data.database.getDatabaseBuilder
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }
    factoryOf(::IosRegionDataSource) bind RegionDataSource::class
}