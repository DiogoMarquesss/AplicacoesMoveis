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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ViewModels.CreateUserViewModel
import com.example.firebase.ui.theme.FireBaseTheme

@Composable
fun CreateUserView(
    modifier: Modifier = Modifier,

){
    val viewModel: CreateUserViewModel = viewModel()
    val uiState by viewModel.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row {
            TextField(
                value = uiState.name ?: "",
                label = { Text("Name") },
                modifier = Modifier.padding(8.dp),
                onValueChange = { viewModel.updateName(it) }
            )

            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    viewModel.AddUserInfo()
                }
            ) {
                Text("Concluir")
            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateUserView(){
    FireBaseTheme() {
        CreateUserView()
    }
}