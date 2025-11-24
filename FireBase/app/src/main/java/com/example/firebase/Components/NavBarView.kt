package com.example.firebase.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.Cart.cartView
import com.example.firebase.R
import com.example.firebase.Login.AuthSession
import com.example.firebase.Login.LoginViewModel

@Composable
fun NavBarView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
){


    val viewModel: LoginViewModel = viewModel()
    val uiState by viewModel.uiState

    val isLogged by AuthSession.isLogged

    Row(
        Modifier.fillMaxWidth()
            .background(Color.Gray)
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)

    ) {
        Button(
            onClick = {
                navController.navigate("home")
            }
        ) {
            Text("Home")
        }

        if(isLogged){

            Button(
                onClick = {
                    navController.navigate("addItem")
                }
            ) {
                Text("Add")
            }
            IconButton(
                onClick = {
                    navController.navigate("Profile")
                }
            ) {
                Image(
                    painter = painterResource(id  = R.drawable.profile_icon),
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            }
            Button(
                onClick = {
                    viewModel.logOut()
                    navController.navigate("home")
                }
            ) {
                Text("LogOut")
            }
            cartView(modifier = Modifier,
                navController = navController
            )

        }
        else{
            Button(
                onClick = {
                    navController.navigate("login")
                }
            ) {
                Text("Login")
            }
        }

    }
}
