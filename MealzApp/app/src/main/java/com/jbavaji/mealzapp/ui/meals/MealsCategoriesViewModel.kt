package com.jbavaji.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jbavaji.mealzapp.model.MealsRepository
import com.jbavaji.mealzapp.model.response.MealsCategoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
) : ViewModel() {

    private val mealsJob = Job()

    init {
        //  Custom Scope using Job
        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        scope.launch {
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

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }
}