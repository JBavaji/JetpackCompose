package com.jbavaji.mealzapp.ui.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
import com.jbavaji.mealzapp.model.response.dummyMealsCategoriesResponse
import com.jbavaji.mealzapp.ui.theme.MealzAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealCategoryDetailScreen(meal: MealsCategoryResponse?) {
    var isExpanded = remember {
        mutableStateOf(false)
    }
    val imageSizeDp: Dp by animateDpAsState(
        targetValue = if(isExpanded.value) 200.dp else 100.dp
    )

    Column {
        Row {
            Card {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meal?.imageUrl)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "Meal category image",
                    modifier = Modifier
                        .size(imageSizeDp)
                        .padding(8.dp),
                    contentScale = ContentScale.Inside
                )
            }
            Text(
                text = meal?.name ?: "default category name",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Button(
            onClick = { isExpanded.value = !isExpanded.value },
            modifier = Modifier.padding(16.dp)
        ) {
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