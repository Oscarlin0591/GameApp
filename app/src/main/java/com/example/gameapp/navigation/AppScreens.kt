package com.example.gameapp.navagation

import java.lang.IllegalArgumentException



enum class AppScreens {
    HomeScreen,
    DetailScreen,
    AboutScreen;
    companion object {
        fun fromRoute (route: String?): AppScreens
            = when(route?.substringBefore("/"))
            {
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                AboutScreen.name -> AboutScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }

    }

}


