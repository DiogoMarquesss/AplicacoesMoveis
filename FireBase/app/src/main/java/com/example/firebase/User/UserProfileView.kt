package com.example.firebase.User

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.Login.LoginViewModel
import com.example.firebase.User.UserProfileViewModel
import com.example.firebase.ui.theme.FireBaseTheme
import com.example.firebase.ui.theme.LightGrey
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@Composable
fun UserProfileView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {

    val viewModel: UserProfileViewModel = viewModel()
    val uiState by viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.updateUserId()
    }

    LaunchedEffect(uiState.id) {
        if (uiState.id.isNotEmpty()) {
            viewModel.getUserInfo(uiState.id)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                viewModel.updateIsEditing()
            }
        ) {}
        if (uiState.isEditing){
            TextField(
                value = uiState.name ?: "",
                label = { Text("Name") },
                modifier = Modifier.padding(8.dp),
                onValueChange = { newValue ->
                    viewModel.updateName(newValue)
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
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    viewModel.updateUserInfo(uiState.id)
                }
            ) {
                Text("Update Info")
            }
        }
        else{
            Text(
                text = "Nome: ${uiState.name}",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Morada: ${uiState.address}",
                fontSize = 20.sp
            )
        }
    }
}


@Preview (showBackground = true)
@Composable
fun PreviewUserProfile(){
    FireBaseTheme() {
        UserProfileView(
            modifier = Modifier

        )
    }
}