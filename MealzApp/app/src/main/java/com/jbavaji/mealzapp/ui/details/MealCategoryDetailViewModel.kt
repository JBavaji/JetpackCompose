package com.jbavaji.mealzapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jbavaji.mealzapp.model.MealsRepository
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse

class MealCategoryDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()
    var mealState = mutableStateOf<MealsCategoryResponse?>(null)

    init {
        val mealCategoryId = savedStateHandle.get<String>("meal_category_id") ?: ""
        mealState.value = repository.getMeal(mealCategoryId)
    }
}