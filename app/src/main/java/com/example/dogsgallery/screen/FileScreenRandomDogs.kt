package com.example.dogsgallery.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.dogs.R
import com.example.dogsgallery.model.AppViewModel

@Composable
fun ScreenRandomDogs(navController: NavHostController, appModel: AppViewModel) {
    val state = appModel.state
    val cantidad = appModel.stateRandom
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TopBar("Random", navigate = navController)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.width(30.dp))

               InputText(
                   value = cantidad.cantidad,
                   onValueChange = {data -> appModel.changeStateRandom(data)},
                   label = "Cantidad"
               )

                Spacer(modifier = Modifier.width(30.dp))

                val color = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF46A6BB)
                )

                Button(
                    onClick = { appModel.changeStateDogs(cantidad.cantidad) },
                    colors = color
                ) {
                    Text(
                        text = "Buscar",
                        fontSize = 25.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(state.dogs) { dog ->
                    DogCard(dog = dog, appModel, context , )
                }

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputText(
    label:String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    value:String,
    onValueChange:(String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, fontSize = 15.sp, color = Color.Black)},
        textStyle = TextStyle(color = Color.Black, fontSize = 25.sp),
        keyboardOptions = keyboardOptions,
        modifier = Modifier.width(100.dp)
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title:String, navigate:NavHostController, space:Int = 95){

    Spacer(modifier = Modifier.height(30.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = { navigate.navigate(Routes.Menu.route)},
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "return",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )

        }

        Spacer(modifier = Modifier.width(space.dp))

        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DogCard(dog: String, appModel: AppViewModel, context: Context, screen: Int = 0) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {

        Card(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = dog,
                    contentDescription = "perro",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(30.dp))

                IconButton(onClick = {
                    if (screen == 0) {
                        appModel.insertDog(dog)
                        Toast.makeText(
                            context,
                            "Agregado exitosamente a favoritos",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }) {
                    Icon(
                        modifier = Modifier
                            .size(100.dp),
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = "Favorites",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun RandomDogs() {
    ScreenRandomDogs(navController = NavHostController(LocalContext.current), appModel = AppViewModel(LocalContext.current))
}