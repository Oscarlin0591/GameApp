package com.example.gameapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
//import androidx.compose.material.Scaffold

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.gameapp.MyApp
import com.example.gameapp.api.Games
import com.example.gameapp.model.GameViewModel
import com.example.gameapp.navagation.AppBar
import com.example.gameapp.navagation.AppScreens
import com.example.gameapp.navagation.GameNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, gameViewModel: GameViewModel
)
    {
        val gameResult = gameViewModel.gameResult.observeAsState()
        val gameList = gameResult.value?.body()
        val gameListNonNullable = gameList?.filterNotNull() ?: emptyList() // elvis operator returns the list else returns emptyList()

    Scaffold(
        topBar = {
            AppBar(currentScreen = AppScreens.HomeScreen.name,
                navController = navController,
                navigateUp = {navController.navigateUp()},
                context = LocalContext.current,
                textToShare = "",
                modifier = Modifier)
        }
        ,
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.Black,
                modifier = Modifier) {}
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  gameViewModel.updateColor(Color.Blue) }) {
                Icon(Icons.Default.Settings, contentDescription = null)
            }
        },
        containerColor = gameViewModel.backgroundColor
    ) {
        Column(modifier = Modifier.padding(vertical = 84.dp, horizontal = 12.dp)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ){
                items(gameListNonNullable) {
                    // added Game row here
                    GameRow(game = it) { game ->
                        navController.navigate(route = AppScreens.DetailScreen.name + "/$game")
                    }
                }
            }
        }
    }
}

@Composable
fun GameRow(game: Games, itemClick: (String)-> Unit = {} ) {
    //This composable contains the cards for each game

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxSize(.5f)
        .clickable {
            //add itemclick
            itemClick(game.title)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),) {
        Column(modifier = Modifier.padding(5.dp)) { // each card lines items with
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(90.dp)
                ,
                shape = RectangleShape,
            ) {
                Image(
                    painter = rememberImagePainter(data = game.thumbnail),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Game Thumbnail"
                )


            }

            Text(
                text = game.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start=10.dp)
            )
            Text(
                text = "Genre: ${game.genre}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start=10.dp)
            )
            Text(
                text = "Release Date: ${game.release_date}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start=10.dp)
            )

        }



    }


}
