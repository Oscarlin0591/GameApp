package com.example.gameapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
//import androidx.compose.material.Scaffold

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.gameapp.api.Games
import com.example.gameapp.model.GameViewModel
import com.example.gameapp.navigation.AppBar
import com.example.gameapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, gameViewModel: GameViewModel
) {
    val gameResult =
        gameViewModel.gameResult.observeAsState() // observable of the live data allows recomposition when data changes
    val gameList = gameResult.value?.body() // list of objects from the live data
    val gameListNonNullable =
        gameList?.filterNotNull() ?: emptyList() //filters null objects within the list.

    Scaffold(
        topBar = { //top app bar
            AppBar(
                currentScreen = AppScreens.HomeScreen.name,
                navController = navController,
                navigateUp = { navController.navigateUp() },
                context = LocalContext.current,
                textToShare = "",
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
        Column(modifier = Modifier.padding(top=84.dp, bottom = 50.dp, start = 8.dp, end = 8.dp)) {
            LazyVerticalGrid(
                //lazy column for all the games
                columns = GridCells.Fixed(2),
            ) {
                items(gameListNonNullable) {
                    // added Game row here
                    GameCard(game = it) { game ->
                        navController.navigate(route = AppScreens.DetailScreen.name + "/$game")
                    }
                }
            }
        }
    }
}

@Composable
fun GameCard(game: Games, itemClick: (String) -> Unit = {}) {
    //This composable contains the cards for each game

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(.5f)
            .height(250.dp)
            .clickable {
                //add itemclick
                itemClick(game.title)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) { // each card lines items with
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RectangleShape,
            ) {
                Image( //image for the game card
                    painter = rememberImagePainter(data = game.thumbnail),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Game Thumbnail"
                )


            }

            Text( //game title
                text = game.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text( // game genre
                text = "Genre: ${game.genre}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(//game release date
                text = "Release Date: ${game.release_date}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 10.dp)
            )

        }
    }
}

