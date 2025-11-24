package com.example.firebase.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ViewModels.AddItemsViewModel
import com.example.firebase.ViewModels.LoginViewModel

@Composable
fun AddItemsView(
    modifier: Modifier = Modifier,
    navController: NavController  = rememberNavController()
){
    val viewModel : AddItemsViewModel = viewModel()
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(15.dp)
    ){
        Column(){
            Row(){
                TextField(
                    value = uiState.name ?: "",
                    label = { Text("Name") },
                    modifier = Modifier.padding(8.dp),
                    onValueChange = { newValue ->
                        viewModel.updateItemName(newValue)
                    }
                )
            }
            Button(onClick = {
                viewModel.addItems()
            }) {
                Text("Adicionar")
            }
        }
    }
}