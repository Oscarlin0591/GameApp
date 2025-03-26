package com.example.gameapp.navigation

import java.lang.IllegalArgumentException


enum class AppScreens {
    HomeScreen,
    DetailScreen,
    AboutScreen,//added about screen enum
    ColorScreen;
    companion object {
        fun fromRoute (route: String?): AppScreens
            = when(route?.substringBefore("/"))
            {
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                AboutScreen.name -> AboutScreen
                ColorScreen.name -> ColorScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }

    }

}


