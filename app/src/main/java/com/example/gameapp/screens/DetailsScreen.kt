package com.example.gameapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gameapp.model.GameViewModel
import com.example.gameapp.navagation.AppBar
import com.example.gameapp.navagation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(
    navController: NavController, gameViewModel: GameViewModel,
    gameTitle: String?
) {
    val gameResult = gameViewModel.gameResult.observeAsState()
    val gameList = gameResult.value?.body()
    val gameListNonNullable = gameList?.filterNotNull() ?: emptyList() //filters non-null items and adds it to the List
    val gameFiltered = gameList?.filter { game -> //sorts game list by title
        game.title == gameTitle
    }
    Scaffold(
        topBar = {
            gameFiltered?.get(0)?.let {
                AppBar(
                    currentScreen = AppScreens.DetailScreen.name,
                    navController = navController,
                    navigateUp = { navController.navigateUp() },
                    context = LocalContext.current,
                    textToShare = it.short_description,
                    gameViewModel = gameViewModel,
                    modifier = Modifier
                )
            }
        },
        containerColor = gameViewModel.backgroundColor
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(top = 84.dp)
        ) {
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 24.sp
                    )
                ) {
                    append("Plot: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        shadow = Shadow(Color.Black, Offset(1f,1f), 0.2f),
                        fontSize = 20.sp
                    )
                ) {
                    gameFiltered?.get(0)?.let { append(it.short_description) }
                }

            }, modifier = Modifier.padding(6.dp))

            HorizontalDivider(modifier = Modifier.padding(3.dp))
            Text(
                text = "Publisher: ${gameFiltered?.get(0)?.publisher}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Actors: ${gameFiltered?.get(0)?.developer}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Rating: ${gameFiltered?.get(0)?.game_url}",
                style = MaterialTheme.typography.titleLarge
            )


        }


    }

}
