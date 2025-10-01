package io.devdiagon.multisites.ui.screens


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.devdiagon.multisites.ui.screens.detail.DetailScreen
import io.devdiagon.multisites.ui.screens.home.HomeScreen
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Serializable
object HomeRoute
@Serializable
data class DetailsRoute(val siteId: String)

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute>() {
            HomeScreen(
                onSiteClick = { site ->
                    navController.navigate(DetailsRoute(site.id))
                }
            )
        }

        composable<DetailsRoute>() { backStackEntry ->
            val args = backStackEntry.toRoute<DetailsRoute>()
            val siteId = checkNotNull(args.siteId)

            DetailScreen(
                vm = koinViewModel(parameters = { parametersOf(siteId) }),
                onBack = { navController.popBackStack() }
            )
        }

    }
}