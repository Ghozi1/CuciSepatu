package com.example.cucisepatu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cucisepatu.ui.detail.DetailDestination
import com.example.cucisepatu.ui.detail.DetailScreen
import com.example.cucisepatu.ui.edit.EditDestination
import com.example.cucisepatu.ui.edit.EditScreen
import com.example.cucisepatu.ui.home.DestinasiHome
import com.example.cucisepatu.ui.home.HomeScreen
import com.example.cucisepatu.ui.pemesanan.DestinasiEntry
import com.example.cucisepatu.ui.pemesanan.PemesananScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(
            DestinasiHome.route
        ) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
        }
        composable(DestinasiEntry.route) {
            PemesananScreen(navigateBack = {
                navController.popBackStack()
            })

        }

        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.sepatuId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val kontakId = backStackEntry.arguments?.getString(DetailDestination.sepatuId)
            kontakId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditDestination.route}/$kontakId")
                        println("kontakId: $kontakId")
                    }
                )
            }
        }

        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.sepatuId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val kontakId = backStackEntry.arguments?.getString(EditDestination.sepatuId)
            kontakId?.let {
                EditScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}