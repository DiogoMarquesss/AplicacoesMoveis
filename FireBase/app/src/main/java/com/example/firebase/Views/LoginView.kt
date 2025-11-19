package com.example.firebase.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebase.ViewModels.LoginViewModel
import com.example.firebase.ui.theme.FireBaseTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController


@Composable

fun LoginView(
    navController : NavController = rememberNavController(),
    modifier: Modifier = Modifier,
){
    val viewModel : LoginViewModel = viewModel()
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = uiState.email ?: "",
            label = { Text("Email") },
            modifier = Modifier.padding(8.dp),
            onValueChange = { newValue ->
                viewModel.updateEmail(newValue)
            }
        )
        TextField(
            value = uiState.password ?: "",
            label = { Text("Password") },
            modifier = Modifier.padding(8.dp),
            onValueChange = { newValue ->
                viewModel.updatePassword(newValue)
            }
        )


        if (uiState.error != null) {
            Text(
                text = uiState.error ?: "",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Row {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    viewModel.createUser()
                    navController.navigate("userInfo")
                }
            ){
                Text("Criar")
            }

            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    viewModel.login()
                    navController.navigate("profile")

                }
            ){
                Text("Login")
            }
        }
    }
}



