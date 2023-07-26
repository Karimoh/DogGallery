package com.example.dogsgallery.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dogs.R
import com.example.dogsgallery.model.AppViewModel

@Composable
fun ScreenMain(navController: NavHostController, appModel: AppViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "DOG GALLERY",
            fontSize = 35.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Black
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            OptionCard(R.drawable.ic_dog1, "Random", navController)
            Spacer(modifier = Modifier.height(30.dp))
            OptionCard(R.drawable.d2, "Buscar", navController)
            Spacer(modifier = Modifier.height(30.dp))
            OptionCard(R.drawable.d3, "Favorito", navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun   OptionCard(
    drawable: Int,
    title: String,
    navigate: NavHostController,
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(300.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        onClick = {
            if (title == "Random") navigate.navigate(Routes.Random.route)
            if (title == "Buscar") navigate.navigate(Routes.Search.route)
            if (title == "Favorito") navigate.navigate(Routes.Favorites.route)
        }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.width(30.dp))

            Image(
                painter = painterResource(id = drawable),
                contentDescription = title,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = title, fontSize = 35.sp, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Black)
            }
        }

    }
}

@Preview
@Composable
fun PreviewMain() {
    ScreenMain(navController = NavHostController(LocalContext.current), appModel = AppViewModel(LocalContext.current))
}