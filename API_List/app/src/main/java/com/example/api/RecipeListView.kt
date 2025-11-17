package com.example.api

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.api.Models.Recipe


@Composable
fun RecipesListView(modifier: Modifier = Modifier, apiURL: String, navController: NavController){
    val productsViewModel : RecipeListViewModel = viewModel()
    val uiState by productsViewModel.uiState
    RecipesListViewContent(modifier = Modifier,uiState,navController)
    LaunchedEffect(Unit) {
        productsViewModel.fetchRecipes(apiURL)
    }
}

@Composable
fun RecipesListViewContent(
    modifier: Modifier = Modifier,
    uiState: RecipeListState,
    navController: NavController
) {

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else if (uiState.error != null) {
            Text(uiState.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center)
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    items = uiState.recipes,
                ) { index, recipe ->
                    RecipeViewCell(recipe)
                }
            }
        }
    }
}


