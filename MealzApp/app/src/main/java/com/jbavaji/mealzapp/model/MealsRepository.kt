package com.jbavaji.mealzapp.model

import com.jbavaji.mealzapp.model.response.MealsCategoriesResponse

class MealsRepository() {
    fun getMeals(): MealsCategoriesResponse = MealsCategoriesResponse(arrayListOf())
}