package com.example.gameapp.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.gameapp.model.GameViewModel
import com.example.gameapp.navigation.AppBar
import com.example.gameapp.navigation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(
    navController: NavController, gameViewModel: GameViewModel,
    gameTitle: String?
) {
    val gameResult = gameViewModel.gameResult.observeAsState() //live data of the
    val gameList = gameResult.value?.body()
    val gameListNonNullable =
        gameList?.filterNotNull() ?: emptyList() //filters non-null items and adds it to the List
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
        bottomBar = { //bottom app bar for cleaner look of the app
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.Black,
                modifier = Modifier.windowInsetsBottomHeight(insets = WindowInsets(bottom=50.dp))
            ) {}
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
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                ) {
                    append("Game Description: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        shadow = Shadow(Color.Black, Offset(1f, 1f), 0.2f),
                        fontSize = 20.sp
                    )
                ) {
                    gameFiltered?.get(0)?.let { appendLine().append(it.short_description) }
                }

            }, modifier = Modifier.padding(6.dp))
            Text(
                text = "Release Date: ${gameFiltered?.get(0)?.release_date}",
                style = MaterialTheme.typography.headlineSmall
            )

            HorizontalDivider(modifier = Modifier.padding(3.dp))

            Text(
                text = "Publisher: ${gameFiltered?.get(0)?.publisher}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Developer: ${gameFiltered?.get(0)?.developer}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Link: ${gameFiltered?.get(0)?.game_url}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Supported Platforms: ${gameFiltered?.get(0)?.platform}",
                style = MaterialTheme.typography.headlineSmall
            )

            HorizontalDivider(modifier = Modifier.padding(3.dp))

            Image(
                painter = rememberImagePainter(data = gameFiltered?.get(0)?.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )


        }

    }

}
