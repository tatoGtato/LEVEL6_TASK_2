package com.example.level6_task_2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.level6_task_2.data.Results

@Database(entities = [Results::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ResultsDatabase : RoomDatabase() {

    abstract fun resultsDao(): ResultsDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var INSTANCE: ResultsDatabase? = null

        fun getDatabase(context: Context): ResultsDatabase? {
            if (INSTANCE == null) {
                synchronized(ResultsDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ResultsDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}