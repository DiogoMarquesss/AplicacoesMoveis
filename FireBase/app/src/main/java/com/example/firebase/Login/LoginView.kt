package com.example.firebase.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.firebase.User.CreateUserViewModel


@Composable

fun LoginView(
    navController : NavController = rememberNavController(),
    modifier: Modifier = Modifier,
){
    val viewModel : LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState


    val userViewModel : CreateUserViewModel = viewModel()
    val _uiState by userViewModel.uiState

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
                    if (!userViewModel.isDataComplete()) {
                        navController.navigate("createProfile")
                    } else {
                        userViewModel.createUser { success, uid ->
                            if (success) {
                                navController.navigate("userInfo")
                            }
                        }
                    }
                }
            ) {
                Text("Criar")
            }


            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    viewModel.login()
                    navController.navigate("home")

                }
            ){
                Text("Login")
            }

        }

    }
}



