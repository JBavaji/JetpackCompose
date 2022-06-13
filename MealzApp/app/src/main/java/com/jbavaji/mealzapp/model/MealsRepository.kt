package com.jbavaji.mealzapp.model

import com.jbavaji.mealzapp.model.api.MealsWebService
import com.jbavaji.mealzapp.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    fun getMeals(): MealsCategoriesResponse? {
        return webService.getMeals().execute().body()
    }
}