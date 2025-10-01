package io.devdiagon.multisites

import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import io.devdiagon.multisites.data.AndroidRegionDataSource
import io.devdiagon.multisites.data.RegionDataSource
import io.devdiagon.multisites.data.database.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    // Context is already declared when initializing Koin in Android application
    single { getDatabaseBuilder(get()) }
    factory { Geocoder(get()) }
    factory { LocationServices.getFusedLocationProviderClient(androidContext()) }
    factoryOf(::AndroidRegionDataSource) bind RegionDataSource::class
}