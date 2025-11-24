package com.example.firebase.Items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebase.Cart.CartViewModel
import com.example.firebase.Models.Item
import com.example.firebase.Repository.ItemRepository
import com.example.firebase.ui.theme.FireBaseTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun itemView(
    item : Item,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    val viewModel : CartViewModel = viewModel()
    val itemModel : ItemsViewModel = hiltViewModel()

    val auth : FirebaseAuth = Firebase.auth
    Card (
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable{
                onClick()
            },
        shape = RoundedCornerShape(12.dp),
    ){
        Column (
            modifier = Modifier.padding(8.dp),

            ){
            Text(text = item.name ?: "",
                modifier = Modifier.padding(bottom = 10.dp),
                fontSize = 20.sp)
            Text(text = item.price ?: "",
                modifier = Modifier.padding(bottom = 10.dp))
        }
        Button(
            onClick = {
                viewModel.updateItemsInCart(item)
                viewModel.addItemCart(auth.currentUser?.uid ?: "")
            }
        ) {
            Text("Add To Cart")
        }
        Button(
            onClick = {
<<<<<<< HEAD

=======
>>>>>>> parent of a70960e (clean code)
                itemModel.removeItem(item.id)
            }
        ) {
            Text("Remove")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewItemView(){
    val item = Item(name = "Motor", price = "15€")
    FireBaseTheme() {
        itemView(item)
    }
}