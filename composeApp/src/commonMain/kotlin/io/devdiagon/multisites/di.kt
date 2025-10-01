package io.devdiagon.multisites

import androidx.room.RoomDatabase
import io.devdiagon.multisites.data.SitesRepository
import io.devdiagon.multisites.data.SitesService
import io.devdiagon.multisites.data.database.SitesDB
import io.devdiagon.multisites.data.database.SitesDao
import io.devdiagon.multisites.ui.screens.detail.DetailViewModel
import io.devdiagon.multisites.ui.screens.home.HomeViewModel
import io.deviagon.multisites.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single(named("apliKey")) { BuildConfig.API_KEY }
    single<SitesDao> {
        val dbBuilder = get<RoomDatabase.Builder<SitesDB>>()
        dbBuilder.build().sitesDao()
    }
}

val dataModule = module {
    factoryOf(::SitesRepository)
    factoryOf(::SitesService)
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.opentripmap.com"
                    parameters.append("apikey", BuildConfig.API_KEY)
                }
            }
        }
    }
}

val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

expect val nativeModule : Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        // Additional initializations based on the platform
        config?.invoke(this)
        modules(appModule, dataModule, viewModelsModule, nativeModule)
    }
}