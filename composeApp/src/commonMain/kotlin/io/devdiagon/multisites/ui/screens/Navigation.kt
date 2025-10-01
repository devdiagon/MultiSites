package io.devdiagon.multisites.ui.screens


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.devdiagon.multisites.data.SitesRepository
import io.devdiagon.multisites.data.SitesService
import io.devdiagon.multisites.data.database.SitesDao
import io.devdiagon.multisites.ui.screens.detail.DetailScreen
import io.devdiagon.multisites.ui.screens.detail.DetailViewModel
import io.devdiagon.multisites.ui.screens.home.HomeScreen
import io.devdiagon.multisites.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import multisites.composeapp.generated.resources.Res
import multisites.composeapp.generated.resources.api_key
import org.jetbrains.compose.resources.stringResource


@Serializable
object HomeRoute
@Serializable
data class DetailsRoute(val siteId: String)

@Composable
fun Navigation(sitesDao: SitesDao) {
    val navController = rememberNavController()
    val repository = rememberSitesRepository(sitesDao)

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute>() {
            HomeScreen(
                onSiteClick = { site ->
                    navController.navigate(DetailsRoute(site.id))
                },
                vm = viewModel { HomeViewModel(repository) }
            )
        }

        composable<DetailsRoute>() { backStackEntry ->
            val args = backStackEntry.toRoute<DetailsRoute>()
            val siteId = checkNotNull(args.siteId)

            DetailScreen(
                vm = viewModel { DetailViewModel(siteId, repository) },
                onBack = { navController.popBackStack() }
            )
        }

    }
}

@Composable
private fun rememberSitesRepository(
    sitesDao: SitesDao,
    apiKey: String = stringResource(Res.string.api_key)
): SitesRepository = remember {

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.opentripmap.com"
                parameters.append("apikey", apiKey)
            }
        }
    }

    SitesRepository(SitesService(client), sitesDao)
}