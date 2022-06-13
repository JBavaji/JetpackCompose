package com.jbavaji.mealzapp.model.response

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponse(private val categories: List<MealsCategoryResponse>)

data class MealsCategoryResponse(
    @SerializedName("idCategory") private val id: String,
    @SerializedName("strCategory") private val name: String,
    @SerializedName("strCategoryThumb") private val imageUrl: String,
    @SerializedName("strCategoryDescription") private val description: String
)