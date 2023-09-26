package com.example.lab4

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ChuckView (private val repository: ChuckRepository) : ViewModel() {
    val currentJoke: LiveData<JokeData> = repository.currentJoke
    val allJokes: LiveData<List<JokeData>> = repository.allJokes

    fun addJoke (joke: ChuckData){
        viewModelScope.launch {
            repository.addJoke(joke.value)
        }

    }
}




// This factory class allows us to define custom constructors for the view model
class ChuckViewFactory(private val repository: ChuckRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChuckView::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChuckView(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}