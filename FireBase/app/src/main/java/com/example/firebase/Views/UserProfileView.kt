package com.example.firebase.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ViewModels.LoginViewModel
import com.example.firebase.ViewModels.UserProfileViewModel
import com.example.firebase.ui.theme.FireBaseTheme
import com.example.firebase.ui.theme.LightGrey
import kotlin.math.log


@Composable
fun UserProfileView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
){


    val viewModel: UserProfileViewModel = viewModel()
    val uiState = viewModel.uiState.value

    val loginViewModel : LoginViewModel = viewModel()
    val loginUiState = loginViewModel.uiState.value

    Column(
        modifier= Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = modifier
                .padding(30.dp)
                .background(LightGrey)
                .border(0.5.dp, Color.Black, shape = RoundedCornerShape(5.dp))

        ){
            Row() {
                Text("Nome: ${uiState.name ?: ""}")
                Text("Email: ${loginUiState.email ?: ""}")
            }



        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfile(){
    FireBaseTheme() {
        UserProfileView(
            modifier = Modifier

        )
    }
}