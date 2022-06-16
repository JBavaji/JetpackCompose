package com.jbavaji.mealzapp.ui.meals

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
import com.jbavaji.mealzapp.ui.theme.MealzAppTheme

@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.getMealsData()

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal -> MealCategory(meal = meal) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealCategory(meal: MealsCategoryResponse) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Row {
            AsyncImage(
                model = meal.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            ) {
                Text(text = "${meal.name}", style = MaterialTheme.typography.headlineMedium)
            }
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