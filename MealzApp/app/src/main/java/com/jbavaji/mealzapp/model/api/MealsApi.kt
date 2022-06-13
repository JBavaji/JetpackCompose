package com.jbavaji.mealzapp.model.api

import com.jbavaji.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class MealsWebService {

    private var api: MealsApi
    private val MEALSDBAPIKEY = "1"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/${MEALSDBAPIKEY}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMeals(): Call<MealsCategoriesResponse> {
        return api.getMeals()
    }

    interface MealsApi {
        @GET("categories.php")
        fun getMeals(): Call<MealsCategoriesResponse>
    }
}