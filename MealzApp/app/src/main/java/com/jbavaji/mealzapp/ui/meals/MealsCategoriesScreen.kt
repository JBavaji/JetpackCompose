package com.jbavaji.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
import com.jbavaji.mealzapp.model.response.dummyMealsCategoriesResponse
import com.jbavaji.mealzapp.ui.theme.MealzAppTheme

@Composable
fun MealsCategoriesScreen(navigationCallback: (String) -> Unit) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.getMealsData()

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal -> MealCategory(meal = meal, navigationCallback) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealCategory(meal: MealsCategoryResponse, navigationCallback: (String) -> Unit) {
    var isExpanded = remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .clickable { navigationCallback(meal.id) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            AsyncImage(
                model = meal.imageUrl,
                contentDescription = "Meal category image",
                modifier = Modifier
                    .size(88.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(
                    text = "${meal.name}",
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "${meal.description}",
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isExpanded.value) Int.MAX_VALUE else 3,
                    textAlign = TextAlign.Start
                )
            }
            Icon(
                imageVector = if (isExpanded.value)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expandable icon for description",
                modifier = Modifier
                    .padding(16.dp)
                    .align(if (isExpanded.value)
                            Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable { isExpanded.value = !isExpanded.value }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsCategoriesScreenPreview() {
    MealzAppTheme {
        MealCategory(dummyMealsCategoriesResponse[0]) {

        }
    }
}