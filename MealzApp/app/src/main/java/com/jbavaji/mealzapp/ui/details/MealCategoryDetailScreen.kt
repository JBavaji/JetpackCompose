package com.jbavaji.mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
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
    var imageState = remember {
        mutableStateOf(MealCategoryImageState.Normal)
    }
    val transition = updateTransition(targetState = imageState, label = "")
    val imageSizeDp by transition.animateDp(
        targetValueByState = { it.value.size }, label = ""
    )
    val color by transition.animateColor(
        targetValueByState = { it.value.color }, label = ""
    )
    val widthSize by transition.animateDp(
        targetValueByState = { it.value.borderWidth }, label = ""
    )



    Column {
        Row {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(width = widthSize, color = color)
            ) {
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
            onClick = {
                imageState.value = if (imageState.value == MealCategoryImageState.Normal)
                    MealCategoryImageState.Expanded
                else
                    MealCategoryImageState.Normal
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Change state of meal profile picture")
        }
    }
}

enum class MealCategoryImageState(
    val color: Color, val size: Dp, val borderWidth: Dp
) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}

@Preview(showBackground = true)
@Composable
fun MealsCategoryDetailsScreenPreview() {
    MealzAppTheme {
        MealCategoryDetailScreen(dummyMealsCategoriesResponse[0])
    }
}