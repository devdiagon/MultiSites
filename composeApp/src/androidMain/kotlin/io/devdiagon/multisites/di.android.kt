package io.devdiagon.multisites

import io.devdiagon.multisites.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    // Context is already declared when initializing Koin in Android application
    single { getDatabaseBuilder(get()) }
}