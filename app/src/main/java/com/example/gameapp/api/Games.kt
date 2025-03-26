package com.example.gameapp.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//data class for the Game object
@Serializable
data class Games(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerialName(value="short_description")
    val short_description: String,
    @SerialName(value="game_url")
    val game_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @SerialName(value="release_date")
    val release_date: String,
    @SerialName(value="freetogame_profile_url")
    val freetogame_profile_url: String,
)