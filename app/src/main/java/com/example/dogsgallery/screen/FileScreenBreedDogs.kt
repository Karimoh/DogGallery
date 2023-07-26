package com.example.dogsgallery.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.dogsgallery.model.AppViewModel
import com.example.dogsgallery.model.DataStateBreeds
import com.example.dogsgallery.model.DataStateDogsByBreed

@Composable
fun ScreenSearchDogs(navController: NavHostController, appModel: AppViewModel) {
    val state = appModel.stateBreeds
    val stateDogsByBreed = appModel.stateDogsByBreed
    val context = LocalContext.current
    if (state.listBreeds.isEmpty()) appModel.changeStateListBreeds()
    DropDownMenu(
        appModel = appModel,
        state = state,
        stateDogsByBreed,
        context,
        navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    appModel: AppViewModel,
    state: DataStateBreeds,
    stateDogsByBreed: DataStateDogsByBreed,
    context: Context,
    navController: NavHostController
) {
    val icon = if (state.expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TopBar(title = "Buscar", navigate = navController )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = state.selectedItem,
                onValueChange = { appModel.changeStateSelectedItem(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { appModel.changeStateTexFiledSize(it.size.toSize()) },
                label = {
                    Text(
                        text = "Selecciona la raza",
                        fontSize = 20.sp
                    )
                        },
                trailingIcon = {
                    Icon(
                        icon,
                        "",
                        Modifier.clickable { appModel.changeStateExpanded(!state.expanded) })
                },



            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        DropdownMenu(
            expanded = state.expanded,
            onDismissRequest = { appModel.changeStateExpanded(false) },
            modifier = Modifier.width(with(LocalDensity.current) { state.texFiledSize.width.dp })
        ) {
            state.listBreeds.forEach { label ->

                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    text = { Text(text = label) },
                    onClick = {
                        appModel.changeStateDogsByBreed(label,6)
                        appModel.changeStateSelectedItem(label)
                        appModel.changeStateExpanded(false)
                    })
            }

        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(stateDogsByBreed.dogs) { dog ->
                DogCard(dog = dog, appModel, context)
            }
        }
    }

}

@Preview
@Composable
private fun BreedDogs() {
    ScreenSearchDogs(navController = NavHostController(LocalContext.current), appModel = AppViewModel(LocalContext.current))
}