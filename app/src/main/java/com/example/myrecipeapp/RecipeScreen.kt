package com.example.myrecipeapp

import android.R
import android.R.attr.fontWeight
import android.adservices.topics.Topic

import android.graphics.Paint
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import java.nio.file.WatchEvent


@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewstate: MainViewModel.RecipeState,
                 navigateToDetail: (Category)-> Unit){
    val recipeViewModel : MainViewModel = viewModel ()


    Box(modifier = Modifier.fillMaxSize()) {
    when{
        viewstate.loading -> {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center) )}
            viewstate.error != null -> {
                Text("Error occurred")
            }
        else -> {
                categoryScreen(categories = viewstate.list,
                    navigateToDetail)
        }
        }
    }
    }
@Composable
fun categoryScreen(categories: List<Category>,
                   navigateToDetail: (Category)-> Unit){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
    items(categories){
        Category ->
        categoryItem(Category,navigateToDetail)
    }
    }
}
@Composable
fun categoryItem(category: Category,
                 navigateToDetail: (Category)-> Unit){
    Column(modifier = Modifier.fillMaxWidth()
        .padding(8.dp)
        .wrapContentHeight()
        .clickable{navigateToDetail(category)},
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}
