package com.qe170193

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.qe170193.ui.view.AddEditLandScreen
import com.qe170193.ui.view.LandDetailScreen
import com.qe170193.ui.view.LandListScreen
import com.qe170193.ui.view.MapPickerScreen
import com.qe170193.ui.viewmodel.LandViewModel

@Composable
fun LandNavHost(navController: NavHostController, viewModel: LandViewModel) {

    NavHost(navController = navController, startDestination = "land_list") {
        composable("land_list") {
            LandListScreen(
                viewModel = viewModel,
                onItemClick = { land -> navController.navigate("land_detail/${land.id}") },
                onAddClick = { navController.navigate("add_edit/-1") }
            )
        }

        composable("land_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            val land = id?.let { viewModel.getLands().find { it.id == id } }

            if (land != null) {
                LandDetailScreen(
                    land = land,
                    navController = navController,
                    onEdit = { navController.navigate("add_edit/${land.id}") },
                    onDeleteConfirmed = {
                        viewModel.deleteLand(land.id)
                        navController.popBackStack("land_list", inclusive = false)
                    }
                )
            } else {
                LaunchedEffect(Unit) {
                    navController.popBackStack("land_list", inclusive = false)
                }
            }
        }

        composable("add_edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            val land = id?.takeIf { it != -1 }?.let { viewModel.getLands().find { it.id == id } }

            AddEditLandScreen(
                land = land,
                viewModel = viewModel,
                navController = navController
            )
        }


        composable("map_picker?returnTo={returnTo}") { backStackEntry ->
            backStackEntry.arguments?.getString("returnTo")
            MapPickerScreen(navController)
        }
    }
}