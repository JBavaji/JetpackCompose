package com.jbavaji.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import com.jbavaji.mealzapp.model.MealsRepository
import com.jbavaji.mealzapp.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
) : ViewModel() {

    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals() { response: MealsCategoriesResponse? -> successCallback(response) }
    }
}