package com.example.level6_task_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level6_task_2.ui.screens.RockPaperScissorsScreen
import com.example.level6_task_2.ui.theme.LEVEL6_TASK_2Theme
import com.example.level6_task_2.viewmodel.ResultsViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.level6_task_2.ui.screens.GameHistoryScreen
import com.example.level6_task_2.ui.screens.GameScreen
import com.example.level6_task_2.ui.theme.summerpixelwide

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEVEL6_TASK_2Theme {
                Struct()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Struct() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red)
            )
        },
        bottomBar = {
            BottomNav(navController)
        }
    )
    { innerPadding ->
        NavHost(navController, innerPadding)
    }
}

@Composable
fun BottomNav(navController: NavController) {
    BottomNavigation (
        backgroundColor = Color.Red,
        contentColor = Color.Black,
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val screens = listOf(
            RockPaperScissorsScreen.GameScreen,
            RockPaperScissorsScreen.GameHistoryScreen,
        )
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Favorite, contentDescription = null,
                        tint = Color.White,
                    )
                },
                label = { Text(stringResource(screen.labelResourceId),
                    color = Color.White,
                    fontFamily = summerpixelwide) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
private fun NavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    val viewModel: ResultsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RockPaperScissorsScreen.GameScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(RockPaperScissorsScreen.GameScreen.route) {
            GameScreen(viewModel)
        }
        composable(RockPaperScissorsScreen.GameHistoryScreen.route) {
            GameHistoryScreen(viewModel)
        }
    }
}