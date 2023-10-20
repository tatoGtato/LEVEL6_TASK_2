package com.example.level6_task_2.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.level6_task_2.R
import com.example.level6_task_2.db.Converters
import com.example.level6_task_2.ui.theme.botsmaticOutlineItalic
import com.example.level6_task_2.ui.theme.summerpixelwide
import com.example.level6_task_2.utils.Utils
import com.example.level6_task_2.viewmodel.ResultsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameHistoryScreen(
    viewModel: ResultsViewModel
){
    val results = viewModel.resultsBacklog
    val resultState by results.observeAsState()

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.deleteGameBacklog()
                },
                containerColor = Color.Red
            ){
            Icon(Icons.Filled.Delete,"", tint = Color.White)}
        }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            resultState?.sortedBy { it.date }.let { results ->
                if (results != null) {
                    itemsIndexed(
                        items = results,
                        key = { _, result -> result.hashCode() }
                    ) { _, result ->
                        Column (
                            modifier = Modifier.fillMaxSize()
                        ){
                            Divider(thickness = 1.dp, color = Color.Black)
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(text = result.result,
                                    fontFamily = botsmaticOutlineItalic,
                                    fontSize = 30.sp)
                            }
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center

                            ){
                                Text(text = Utils.dateToString(result.date),
                                    fontFamily = summerpixelwide)
                            }
                            Row (
                                modifier = Modifier.padding(
                                    start = 20.dp, top = 10.dp, bottom = 5.dp, end = 10.dp
                                ).fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ){
                                Image(
                                    painter = painterResource(id = result.PcMove),
                                    contentDescription = "", // decorative element
                                    alignment = Alignment.Center,
                                    modifier = Modifier.graphicsLayer { this.rotationY = 180f
                                    }.size(90.dp)
                                )

                                Text(text = "v.s",
                                    fontFamily = botsmaticOutlineItalic,
                                    fontSize = 25.sp)

                                Image(
                                    painter = painterResource(id = result.playerMove),
                                    contentDescription = "", // decorative element
                                    alignment = Alignment.Center,
                                    modifier = Modifier.size(90.dp)
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}