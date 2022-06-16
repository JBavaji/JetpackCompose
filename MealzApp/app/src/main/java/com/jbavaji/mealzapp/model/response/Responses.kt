package com.jbavaji.mealzapp.model.response

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponse(val categories: List<MealsCategoryResponse>)

data class MealsCategoryResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val imageUrl: String,
    @SerializedName("strCategoryDescription") val description: String
)

val dummyMealsCategoriesResponse: List<MealsCategoryResponse> = arrayListOf(
    MealsCategoryResponse(
        id = "0",
        name = "Chicken",
        imageUrl = "https://www.themealdb.com/images/category/chicken.png",
        description = "Chicken is a type of domesticated fowl, a subspecies of the red junglefowl. It is one of the most common and widespread domestic animals, with a total population of more than 19 billion as of 2011.[1] Humans commonly keep chickens as a source of food (consuming both their meat and eggs) and, more rarely, as pets."
    )
)