package com.example.firebase.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ViewModels.CreateUserViewModel
import com.example.firebase.ViewModels.LoginViewModel
import com.example.firebase.ui.theme.FireBaseTheme

@Composable
fun CreateProfileView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val viewModel: CreateUserViewModel = viewModel()
    val uiState by viewModel.uiState
    val loginState by viewModel.loginState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = uiState.name ?: "",
            label = { Text("Name") },
            modifier = Modifier.padding(8.dp),
            onValueChange = { newValue ->
                viewModel.updateName(newValue)
            }
        )
        TextField(
            value = loginState.email ?: "",
            label = { Text("Email") },
            modifier = Modifier.padding(8.dp),
            onValueChange = { newValue ->
                viewModel.updateEmail(newValue)
            }
        )
        TextField(
            value = uiState.address ?: "",
            label = { Text("Address") },
            modifier = Modifier.padding(8.dp),
            onValueChange = { newValue ->
                viewModel.updateAddress(newValue)
            }
        )
        TextField(
            value = loginState.password ?: "",
            label = { Text("Password") },
            modifier = Modifier.padding(8.dp),
            onValueChange = { newValue ->
                viewModel.updatePassword(newValue)
            }
        )

        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                viewModel.createUser { success ->
                    if (success) {
                        viewModel.AddUserInfo()
                        navController.navigate("Home")
                    }
                }
            }
        ) {
            Text("Criar")
        }
    }
}



@Preview (showBackground = true)
@Composable
fun PreviewCreateProfileView(){
    FireBaseTheme() {
        CreateProfileView()
    }
}