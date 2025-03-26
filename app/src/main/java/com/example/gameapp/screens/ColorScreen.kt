package com.example.gameapp.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gameapp.model.GameViewModel
import com.example.gameapp.navigation.AppBar
import com.example.gameapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun ColorScreen(
    navController: NavController,
    gameViewModel: GameViewModel
) {
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = AppScreens.AboutScreen.name,
                navController = navController,
                navigateUp = { navController.navigateUp() },
                textToShare = "",
                context = LocalContext.current,
                gameViewModel = gameViewModel,
                modifier = Modifier
            )
        },
        containerColor = gameViewModel.backgroundColor
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
        ) {
            Text("Change the background color by tapping a color:", style = MaterialTheme.typography.titleLarge)
            Card(onClick = {gameViewModel.updateColor(color = Color.White)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Box(Modifier
                    .background(Color.White)
                    .weight(1f)
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)){

                }
                Text(text = "White", modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Card(onClick = {gameViewModel.updateColor(color = Color.Black)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Box(Modifier
                    .background(Color.Black)
                    .weight(1f)
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)){

                }
                Text(text = "Black", modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Card(onClick = {gameViewModel.updateColor(color = Color.Gray)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Box(Modifier
                    .background(Color.Gray)
                    .weight(1f)
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)){

                }
                Text(text = "Gray", modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Card(onClick = {gameViewModel.updateColor(color = Color.Green)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Box(Modifier
                    .background(Color.Green)
                    .weight(1f)
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)){

                }
                Text(text = "Green", modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Card(onClick = {gameViewModel.updateColor(color = Color.Magenta)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Box(Modifier
                    .background(Color.Magenta)
                    .weight(1f)
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)){

                }
                Text(text = "Magenta", modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Card(onClick = {gameViewModel.updateColor(color = Color.Yellow)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Box(Modifier
                    .background(Color.Yellow)
                    .weight(1f)
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)){

                }
                Text(text = "Yellow", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }


    }
}

