package com.example.gameapp.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.api.ApiInterface
import com.example.gameapp.api.Games
import kotlinx.coroutines.launch
import retrofit2.Response


class GameViewModel : ViewModel(){

    // create an instance of the api
    private val gameApi = ApiInterface.create()

    val _gameResult = MutableLiveData<Response<ArrayList<Games>>>() //Liva data of the retrieved games array list
    val gameResult : LiveData<Response<ArrayList<Games>>> = _gameResult //exposed live data of the games array list

    var backgroundColor by mutableStateOf(Color.Green) //initial background color

    fun getData(){ // function to get games from the API interface
        viewModelScope.launch { //launches coroutine to retrieve data
            try {
                val response = gameApi.getGames() //getGames() method call
                if(response.isSuccessful) { //blocking operation
                    Log.d("API response", response.body().toString())
                    _gameResult.value = response
                } else {
                    Log.d("Network error", "Failed to load data")
                }
            } catch (e: Exception) {
                e.message?.let{Log.d("Network error", "Failed to load data")}
            }
        }
    }

    fun updateColor(color: Color){ //update color method for background
        backgroundColor = color
    }
}