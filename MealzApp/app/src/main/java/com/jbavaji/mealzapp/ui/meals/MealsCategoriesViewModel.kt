package com.jbavaji.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import com.jbavaji.mealzapp.model.MealsRepository
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
) : ViewModel() {

    fun getMeals(): List<MealsCategoryResponse> {
        return repository.getMeals()?.categories.orEmpty()
    }
}