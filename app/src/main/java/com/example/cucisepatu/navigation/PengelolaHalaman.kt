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
import com.example.cucisepatu.ui.jenissepatu.DestinasiJenis
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
                navigateToJenisSepatu = {
                    navController.navigate(DestinasiJenis.route)
                },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                }
            )
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
            val sepatuId = backStackEntry.arguments?.getString(DetailDestination.sepatuId)
            sepatuId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditDestination.route}/$sepatuId")
                        println("kontakId: $sepatuId")
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
            val sepatuId = backStackEntry.arguments?.getString(EditDestination.sepatuId)
            sepatuId?.let {
                EditScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}