package com.example.gameapp.navagation

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gameapp.model.GameViewModel
import com.example.gameapp.screens.AboutScreen

import com.example.gameapp.screens.home.HomeScreen
import com.example.gameapp.screens.details.DetailsScreen
import com.example.gameapp.screens.home.HomeScreenLandscape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: String,
    navController: NavController,
    navigateUp: () -> Unit,
    textToShare: String,
    context: Context,
    gameViewModel: GameViewModel,
    modifier: Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack = backStackEntry?.destination?.route != AppScreens.HomeScreen.name
    Log.d("canNavigateBack", canNavigateBack.toString());
    TopAppBar(
        title = { Text("Game App") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier,
        navigationIcon = { // takes the "canNavigateBack" boolean and displays an Icon that is clickable with the navigateUp method
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }
        },
        actions = { // actions that include SHARE, SETTINGS, and ABOUT.
            if (textToShare.isNotEmpty()) {
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_SUBJECT, "Here is a cool game I found!")
                        putExtra(Intent.EXTRA_TEXT, textToShare)
                    }
                    context.startActivity(Intent.createChooser(intent, "Share Option"))
                }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share items")
                }
            }
            IconButton( // icon and clickable to direct user to the color picker screen
                onClick = {gameViewModel.updateColor(Color.Blue)}
            ) {
                Icon(Icons.Default.Settings, contentDescription = null)
            }
            IconButton( // icon and clickable to direct user to the about screen
                onClick = {navController.navigate(route = AppScreens.AboutScreen.name)}
            ) {
                Icon(Icons.Default.Info, contentDescription = null)
            }
        },
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameNavigation() {
    val navController = rememberNavController()
    val gameViewModel: GameViewModel = viewModel()
    gameViewModel.getData() // creating an instance of the viewModel

    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name,
        modifier = Modifier.fillMaxSize()

    ) {
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                gameViewModel
            ) // passes the viewModel to the HomeScreen
        }
        composable(AppScreens.DetailScreen.name + "/{title}",
            arguments = listOf(navArgument(name = "title") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController, gameViewModel,
                backStackEntry.arguments?.getString("title"),
            )
        }

        composable(AppScreens.AboutScreen.name) {
            AboutScreen(
                navController = navController,
                gameViewModel
            )
        }
    }
}
