package com.example.firebase.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ui.theme.Azure

@Composable
fun HomeView(
    modifier: Modifier,
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .background(Azure)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ){
                Button(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterEnd)
                        .border(
                        5.dp,
                        Color.Black,
                        shape = CircleShape,
                    ),

                    onClick = {
                        navController.navigate("login")
                    }
                ){}
                Button(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterEnd)
                        .border(
                            5.dp,
                            Color.Black,
                            shape = CircleShape,
                        ),
                    onClick = {
                        navController.navigate("addItem")
                    }
                ){}
            }

        }
    }
}