package com.example.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase.Items.AddItemsView
import com.example.firebase.User.CreateProfileView
import com.example.firebase.User.CreateUserView
import com.example.firebase.Home.HomeView
import com.example.firebase.Login.LoginView
import com.example.firebase.User.UserProfileView
import com.example.firebase.ui.theme.FireBaseTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FireBaseTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("login") {
                            LoginView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable("profile") {
                            UserProfileView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )

                        }
                        composable ("home"){
                            HomeView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable ("addItem"){
                            AddItemsView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController)
                        }

                        composable ("userInfo"){
                            CreateUserView(
                                modifier = Modifier.padding(innerPadding),
                            )
                        }
                        composable ("createProfile"){
                            CreateProfileView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController)
                        }
                    }
                }
            }
        }
    }
}

