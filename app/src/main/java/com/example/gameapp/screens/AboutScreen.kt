package com.example.gameapp.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gameapp.model.GameViewModel
import com.example.gameapp.navigation.AppBar
import com.example.gameapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun AboutScreen(
    navController: NavController,
    gameViewModel: GameViewModel
) {
    Scaffold(
        topBar = { //top app bar
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

        Column( //page body with text and info
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(top = 84.dp)
        ) {
            Text(
                "This app uses the FreeToGame api which includes over 400" +
                        " free-to-play games. For the HomeScreen, we used the endpoint of /games" +
                        " sorted by ", modifier = Modifier.padding(6.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(3.dp))
            Text(
                text = "This app is made by Jacob Levin and Oscar Lin",
                style = MaterialTheme.typography.titleLarge
            )
        }


    }
}