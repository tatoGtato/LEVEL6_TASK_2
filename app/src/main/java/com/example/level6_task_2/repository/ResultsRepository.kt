package com.example.level6_task_2.repository

import android.content.Context
import com.example.level6_task_2.data.Results
import com.example.level6_task_2.db.ResultsDao
import com.example.level6_task_2.db.ResultsDatabase

class ResultsRepository(context: Context) {

    private val resultsDao: ResultsDao

    init {
        val database = ResultsDatabase.getDatabase(context)
        resultsDao = database!!.resultsDao()
    }

    suspend fun insert(result: Results) = resultsDao.insert(result)

    suspend fun delete(result: Results) = resultsDao.delete(result)

    suspend fun deleteAll() = resultsDao.deleteAll()

    fun getResults() = resultsDao.getResults()

    fun getWins() = resultsDao.getWins()
    fun getLooses() = resultsDao.getLooses()
    fun getTies() = resultsDao.getTies()
}