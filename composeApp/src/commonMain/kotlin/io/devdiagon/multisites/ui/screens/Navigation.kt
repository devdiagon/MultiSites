package io.devdiagon.multisites.ui.screens


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.devdiagon.multisites.data.sites
import io.devdiagon.multisites.ui.screens.detail.DetailScreen
import io.devdiagon.multisites.ui.screens.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
object HomeRoute
@Serializable
data class DetailsRoute(val siteId: Int)

@Composable
fun Navigation(modifier: Modifier = Modifier) {
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
            DetailScreen(
                site = sites.first { it.id == args.siteId },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
