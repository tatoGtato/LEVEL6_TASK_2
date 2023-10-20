package com.example.level6_task_2.ui.screens

import androidx.annotation.StringRes
import com.example.level6_task_2.R

sealed class RockPaperScissorsScreen(
    val route: String,
    @StringRes val labelResourceId: Int
) {
    object GameScreen : RockPaperScissorsScreen("game_screen", R.string.play)
    object GameHistoryScreen : RockPaperScissorsScreen("game_history_screen", R.string.history)
}