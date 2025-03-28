package com.example.gameapp.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.unit.sp
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
                currentScreen = AppScreens.ColorScreen.name,
                navController = navController,
                navigateUp = { navController.navigateUp() },
                textToShare = "",
                context = LocalContext.current,
                gameViewModel = gameViewModel,
                modifier = Modifier
            )
        },
        bottomBar = { //bottom app bar for cleaner look of the app
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.Black,
                modifier = Modifier.windowInsetsBottomHeight(insets = WindowInsets(bottom=50.dp))
            ) {}
        },
        containerColor = gameViewModel.backgroundColor
    ) {

        Column( // column of buttons that change the background color of each screen
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .height(750.dp)
        ) {
            Text("Change the background color by tapping a color:", style = MaterialTheme.typography.titleLarge)
            //white button
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
            //black button
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
            //gray button
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
            //green button
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
            //magenta button
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
            //yellow button
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

