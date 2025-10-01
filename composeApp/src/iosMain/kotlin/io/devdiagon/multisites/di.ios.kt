package io.devdiagon.multisites

import io.devdiagon.multisites.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }
}