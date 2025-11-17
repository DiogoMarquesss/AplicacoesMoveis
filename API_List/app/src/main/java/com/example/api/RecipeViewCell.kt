package com.example.api

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api.Models.Recipe
import com.example.api.ui.theme.ApiTheme
import coil3.compose.AsyncImage


@Composable
fun RecipeViewCell(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Card (
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable{
                onClick()
            },
        shape = RoundedCornerShape(12.dp), // Apply rounded corners

    ){
        Column (
            modifier = Modifier.padding(8.dp),

            ) {
            Text(
                text = recipe.name ?: "",
                modifier = Modifier.padding(bottom = 20.dp, top = 15.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.name,
            )
            Text(
                text = "Tempo de preparação: ${recipe.prepTimeMinutes ?: "N/A"} minutos",
                modifier = Modifier.padding(bottom = 10.dp, top = 15.dp)
            )
            Text(
                text = "Dificuladade: ${recipe.difficulty ?: "N/A"}" ,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "Pessoas: ${recipe.servings?: "N/A"} ",
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleViewCellPreview(){
    ApiTheme {
        RecipeViewCell(
            recipe = Recipe(
                name = "",
                image = "",
                prepTimeMinutes = "",
                difficulty = "",
                servings = "",
            )
        )
    }
}
