package com.jbavaji.mealzapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
import com.jbavaji.mealzapp.model.response.dummyMealsCategoriesResponse
import com.jbavaji.mealzapp.ui.theme.MealzAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealCategoryDetailScreen(meal: MealsCategoryResponse) {
    Column {
        Row {
           Card {
               AsyncImage(
                   model = ImageRequest.Builder(LocalContext.current)
                       .data(meal.imageUrl)
                       .transformations(CircleCropTransformation()),
                   contentDescription = "Meal category image",
                   modifier = Modifier
                       .size(200.dp)
                       .padding(8.dp),
                   contentScale = ContentScale.Inside
               )
           }
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Change state of meal profile picture")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsCategoryDetailsScreenPreview() {
    MealzAppTheme {
        MealCategoryDetailScreen(dummyMealsCategoriesResponse[0])
    }
}