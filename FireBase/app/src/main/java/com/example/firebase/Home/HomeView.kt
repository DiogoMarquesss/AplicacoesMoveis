package com.example.firebase.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.Components.NavBarView
import com.example.firebase.Items.ItemsViewModel
import com.example.firebase.Items.itemView
import com.example.firebase.ui.theme.Azure

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {


    val viewModel: ItemsViewModel = viewModel()
    val uiState by viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.getItemsFromDb()
    }

    LaunchedEffect(uiState.items) {
        if (uiState.items.isNotEmpty()) {
            viewModel.listToItem(uiState.items)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 25.dp)
    ) {
        NavBarView(navController = navController)
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Azure)
        ) {
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn {
                itemsIndexed(
                    items = uiState.items,
                ){
                    index, item ->
                    itemView(item){
                        navController.navigate("profile")
                    }
                }
            }
        }
    }
}

