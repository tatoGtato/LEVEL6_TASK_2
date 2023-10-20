package com.example.level6_task_2.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.level6_task_2.data.Results
import com.example.level6_task_2.repository.ResultsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultsViewModel (application: Application) : AndroidViewModel(application) {
    private val resultsRepository = ResultsRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    val resultsBacklog = resultsRepository.getResults()


    var numberWins = resultsRepository.getWins()
    val numberLooses = resultsRepository.getLooses()
    val numberTies = resultsRepository.getTies()

    fun insertGame(result: Results) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                resultsRepository.insert(result)
            }
        }
    }

    fun deleteGameBacklog() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                resultsRepository.deleteAll()
            }
        }
    }

    fun deleteGame(result: Results) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                resultsRepository.delete(result)
            }
        }
    }

}