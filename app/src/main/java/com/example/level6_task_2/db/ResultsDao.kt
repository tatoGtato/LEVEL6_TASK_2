package com.example.level6_task_2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.level6_task_2.data.Results

@Dao
interface ResultsDao {

    @Query("SELECT * from results ORDER BY `date` ASC")
    fun getResults(): LiveData<List<Results>>

    @Query("SELECT * from results WHERE `result`='win' ")
    fun getWins(): LiveData<List<Results>>

    @Query("SELECT * from results WHERE `result`='lose' ")
    fun getLooses(): LiveData<List<Results>>

    @Query("SELECT * from results WHERE `result`='tie' ")
    fun getTies(): LiveData<List<Results>>

    @Insert
    suspend fun insert(result: Results)

    @Delete
    suspend fun delete(result: Results)

    @Query("DELETE from results")
    suspend fun deleteAll()
}