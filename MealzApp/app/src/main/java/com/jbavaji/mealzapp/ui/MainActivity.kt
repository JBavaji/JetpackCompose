package com.jbavaji.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jbavaji.mealzapp.ui.details.MealCategoryDetailScreen
import com.jbavaji.mealzapp.ui.details.MealCategoryDetailViewModel
import com.jbavaji.mealzapp.ui.meals.MealsCategoriesScreen
import com.jbavaji.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealzApp()
                }
            }
        }
    }
}

@Composable
fun MealzApp() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen() { meal_category_id ->
                navHostController.navigate("destination_meal_details/${meal_category_id}")
            }
        }
        composable(
            route = "destination_meal_details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel: MealCategoryDetailViewModel = viewModel()
            MealCategoryDetailScreen(meal = viewModel.mealState.value)
        }
    }
}