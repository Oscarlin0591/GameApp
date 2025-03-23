package com.example.gameapp.model

import android.util.Log
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

    //LiveData is lifecycle aware, meaning it only updates observers are in an active lifecycle state such as STARTED or RESUMED
    // below two lines is the structure to set up the livedata observable
    val _gameResult = MutableLiveData<Response<ArrayList<Games>>>()
    val gameResult : LiveData<Response<ArrayList<Games>>> = _gameResult


    fun getData(){
    //coroutine: instance of suspendable computation. Conceptually similar to a thread (takes a block of code to run that works concuurently with the rest of the code).
    //however, a coroutine is not boud to any particular thread. It may suspend its execution in one thread and resume in another one.
        viewModelScope.launch {
            try {
                val response = gameApi.getGames()
                if(response.isSuccessful) {
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
}