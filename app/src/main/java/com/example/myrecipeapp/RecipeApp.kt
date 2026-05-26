package com.example.myrecipeapp

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun recipeApp(navController: NavHostController){

    val recipeViewModel : MainViewModel = viewModel ()

    val viewState: MainViewModel.RecipeState by recipeViewModel.categoriesState


    NavHost(navController = navController , startDestination = Screen.recipeScreen.route) {
        composable (Screen.recipeScreen.route){
            RecipeScreen(viewstate =  viewState ,navigateToDetail = {
            navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.detailScreen.route)
            })
        }
        composable  (route = Screen.detailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat") ?: Category("" ,"" , "","")
        categoryDetailScreen(category = category)
        }
    }
}