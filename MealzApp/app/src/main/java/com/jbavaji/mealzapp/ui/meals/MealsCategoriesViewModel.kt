package com.jbavaji.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbavaji.mealzapp.model.MealsRepository
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository.getInstance()
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mealsState.value = getMeals()
        }
    }

    private val mealsState: MutableState<List<MealsCategoryResponse>> = mutableStateOf(emptyList())

    private suspend fun getMeals(): List<MealsCategoryResponse> {
        return repository.getMeals().categories
    }

    fun getMealsData(): List<MealsCategoryResponse> {
        return mealsState.value
    }
}