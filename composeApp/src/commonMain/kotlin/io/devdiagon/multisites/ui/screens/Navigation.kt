package io.devdiagon.multisites.ui.screens


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.devdiagon.multisites.data.SitesRepository
import io.devdiagon.multisites.data.SitesService
import io.devdiagon.multisites.ui.screens.home.HomeScreen
import io.devdiagon.multisites.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
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
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // Temp
    val client = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    val apiKey = stringResource(Res.string.api_key)
    val viewModel = viewModel {
        HomeViewModel(SitesRepository(SitesService(apiKey, client)))
    }

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute>() {
            HomeScreen(
                onSiteClick = { site ->
                    navController.navigate(DetailsRoute(site.id))
                },
                vm = viewModel
            )
        }

        //Navigation feature not implemented with the API
        //composable<DetailsRoute>() { backStackEntry ->
        //    val args = backStackEntry.toRoute<DetailsRoute>()
        //    DetailScreen(
        //        site = sites.first { it.id == args.siteId },
        //        onBack = { navController.popBackStack() }
        //    )
        //}

    }
}
