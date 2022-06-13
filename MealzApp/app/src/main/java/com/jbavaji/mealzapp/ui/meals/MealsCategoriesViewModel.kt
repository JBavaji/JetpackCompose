package com.jbavaji.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import com.jbavaji.mealzapp.model.MealsRepository

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
) : ViewModel() {
}