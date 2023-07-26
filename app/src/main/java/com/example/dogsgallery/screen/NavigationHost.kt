package com.example.dogsgallery.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogsgallery.model.AppViewModel

sealed class Routes(val route: String){
    object Menu: Routes("menu")
    object Random: Routes("random")
    object Search: Routes("buscar")
    object Favorites: Routes("favoritos")
}

@Composable
fun NavigationHost(appModel: AppViewModel) {

    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Menu.route
    ) {
        composable(Routes.Menu.route) {
            ScreenMain(
                navController = navController,
                appModel = appModel
            )
        }
        composable(Routes.Random.route) {
            ScreenRandomDogs(
                navController = navController,
                appModel = appModel
            )        }
        composable(Routes.Search.route) {
            ScreenSearchDogs(
                navController = navController,
                appModel = appModel
            )        }
        composable(Routes.Favorites.route) {
            ScreenWishListDogs(
                navController = navController,
                appModel = appModel
            )
        }
    }
}