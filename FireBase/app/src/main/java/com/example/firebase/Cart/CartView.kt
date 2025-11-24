package com.example.firebase.Cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.R
import com.example.firebase.ui.theme.FireBaseTheme

@Composable
fun cartView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
){
    val viewModel : CartViewModel = viewModel()
    val uiState by viewModel.uiState


    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = {
            expanded = true
        }) {
            Image(
                painter = painterResource(id  = R.drawable.cart),
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp)
            )

        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            ) {
            uiState.items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(item.name)
                            Text(item.price)
                        }
                    },
                    onClick = {
                        expanded = false
                    }
                )
            }
            Button(
                onClick = {}
            ) {
                Text("Finalizar Compra")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewCartView(){
    FireBaseTheme() {
        cartView()
    }
}