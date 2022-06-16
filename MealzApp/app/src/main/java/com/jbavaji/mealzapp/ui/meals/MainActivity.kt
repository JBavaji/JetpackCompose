package com.jbavaji.mealzapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
import com.jbavaji.mealzapp.ui.theme.MealzAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                    MealsCategoriesScreen()
                }
            }
        }
    }
}

@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val rememberedMeals: MutableState<List<MealsCategoryResponse>> = remember {
        mutableStateOf(emptyList<MealsCategoryResponse>())
    }
    val coroutineScope = rememberCoroutineScope()

    // to avoid multiple launch, call once when composable init very first time
    LaunchedEffect(key1 = "GET_MEALS") {
        coroutineScope.launch(Dispatchers.IO) {
            val meals = viewModel.getMeals()
            rememberedMeals.value = meals
        }
    }

    LazyColumn {
        items(rememberedMeals.value) { meal ->
            Text(text = "${meal.name} - ${meal.description} \n")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsCategoriesScreenPreview() {
    MealzAppTheme {
        MealsCategoriesScreen()
    }
}