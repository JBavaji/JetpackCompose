package com.jbavaji.mealzapp.model

import com.jbavaji.mealzapp.model.api.MealsWebService
import com.jbavaji.mealzapp.model.response.MealsCategoriesResponse
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    private var cacheMeals = listOf<MealsCategoryResponse>()

    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cacheMeals = response.categories
        return response
    }

    fun getMeal(id: String): MealsCategoryResponse? {
      return  cacheMeals.firstOrNull { it.id == id }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? =null

        fun getInstance() = instance?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}