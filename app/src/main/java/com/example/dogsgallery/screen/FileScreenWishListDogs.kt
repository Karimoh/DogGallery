package com.example.dogsgallery.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dogsgallery.model.AppViewModel

@Composable
fun ScreenWishListDogs(navController: NavHostController, appModel: AppViewModel) {
    val state = appModel.stateFavorite
    if (state.dogs.isEmpty()) appModel.getDogs()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ) {

        TopBar(title = "Favoritos", navigate = navController, 80)

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.dogs) { dog ->
                DogCard(dog = dog.urlImage, appModel, context, 1)
            }
        }
    }
}

@Preview
@Composable
private fun BreedDogs() {
    ScreenWishListDogs(navController = NavHostController(LocalContext.current), appModel = AppViewModel(LocalContext.current))
}