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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jbavaji.mealzapp.model.response.MealsCategoriesResponse
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
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

    viewModel.getMeals() { meals: MealsCategoriesResponse? ->
        meals?.let {
            rememberedMeals.value = it.categories
        }
    }

    LazyColumn {
        items(rememberedMeals.value) { meal ->
            Text(text = "${meal.name} - ")
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