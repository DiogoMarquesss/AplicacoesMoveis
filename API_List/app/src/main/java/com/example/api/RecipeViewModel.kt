package com.example.api

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.api.Models.Recipe
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class RecipeListState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class RecipeListViewModel: ViewModel(){
    var uiState = mutableStateOf(RecipeListState())

    fun fetchRecipes(source: String) {
        uiState.value = uiState.value.copy(isLoading = true)
        // fetch articles from API or database

        val request = Request.Builder()
            .url(source)
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = "Unexpected code $response"
                        )
                    }

                    val recipesResult = response.body!!.string()
                    val jsonResult = JSONObject(recipesResult)
                    val recipesList = arrayListOf<Recipe>()
                    val RecipesJson = jsonResult.getJSONArray("recipes")
                    for (i in 0 until RecipesJson.length()) {
                        val RecipeJson = RecipesJson.getJSONObject(i)
                        val recipe = Recipe.fromJson(RecipeJson)
                        recipesList.add(recipe)
                    }
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        recipes = recipesList
                    )
                }
            }
        })

        fun fetchRecipeDetails(recipeId: String) {

            fun fetchRecipeDetails(id: String) {
                uiState.value = uiState.value.copy(isLoading = true)

                // monta a URL com base no ID
                val url = "https://dummyjson.com/recipes/$id"

                val request = Request.Builder()
                    .url(url)
                    .build()

                val client = OkHttpClient()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = e.message
                        )
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.use {
                            if (!response.isSuccessful) {
                                uiState.value = uiState.value.copy(
                                    isLoading = false,
                                    error = "Unexpected code $response"
                                )
                                return
                            }

                            val recipeResult = response.body!!.string()
                            val jsonResult = JSONObject(recipeResult)
                            val recipe = Recipe.fromJson(jsonResult)

                            uiState.value = uiState.value.copy(
                                isLoading = false,
                                recipes = listOf(recipe)
                            )
                        }
                    }
                })
            }


        }
    }
}