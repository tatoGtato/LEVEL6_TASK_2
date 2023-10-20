package com.example.level6_task_2.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.level6_task_2.R
import com.example.level6_task_2.data.Results
import com.example.level6_task_2.ui.theme.botsmaticOutlineItalic
import com.example.level6_task_2.ui.theme.summerpixelwide
import com.example.level6_task_2.viewmodel.ResultsViewModel
import java.util.Date

@Composable
fun GameScreen(
    viewModel: ResultsViewModel
){

//    var wins = remember { mutableStateOf(winsList?.size?:0) }
    val wins = viewModel.numberWins
    val wisnState by wins.observeAsState()

    val ties = viewModel.numberTies
    val tiesState by ties.observeAsState()

    val loses = viewModel.numberLooses
    val loseState by loses.observeAsState()

    var result = remember { mutableStateOf("") }
    var playerPick = remember { mutableStateOf(0) }
    var computerPick = remember { mutableStateOf(0) }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
       // horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            horizontalArrangement = Arrangement.Center
        ){
            Text(stringResource(id = R.string.title),
                fontFamily = botsmaticOutlineItalic,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 35.sp,  // Here line height
                ))
        }
        Row (
            horizontalArrangement = Arrangement.Center
        ){
            Text(stringResource(id = R.string.instructions),
                textAlign = TextAlign.Center,
                fontFamily = summerpixelwide,
                fontSize = 20.sp)
        }
        Row {
            Text(stringResource(id = R.string.stats),
                fontFamily = summerpixelwide,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 10.dp))
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(stringResource(id = R.string.win) + " " + wisnState?.size.toString(), fontFamily = summerpixelwide,)
            Text(stringResource(id = R.string.draw) + " " + tiesState?.size.toString(), fontFamily = summerpixelwide,)
            Text(stringResource(id = R.string.lose) + " " + loseState?.size.toString(), fontFamily = summerpixelwide,)

        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Color.Black)
            ){
                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    if (playerPick.value != 0 && computerPick.value != 0){
                        Image(
                            painter = painterResource(id = computerPick.value),
                            contentDescription = "", // decorative element
                            alignment = Alignment.Center,
                            modifier = Modifier.graphicsLayer { this.rotationY = 180f
                            }.size(100.dp)
                        )
                        Text(text = "v.s",
                            fontFamily = botsmaticOutlineItalic,
                            fontSize = 35.sp)
                        Image(
                            painter = painterResource(id = playerPick.value),
                            contentDescription = "", // decorative element
                            alignment = Alignment.Center,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(stringResource(id = R.string.computer), fontFamily = botsmaticOutlineItalic)

            Text(stringResource(id = R.string.you),
                modifier = Modifier.padding(end = 5.dp),
                fontFamily = botsmaticOutlineItalic)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ){
            Box (
                modifier = Modifier.clickable {
                    playerPick.value = R.drawable.rockrigth
                    computerPick.value = computerPicker()
                    result.value = CheckResult(playerPick.value, computerPick.value)
                    viewModel.insertGame(getResult(result.value,playerPick.value, computerPick.value))
                }
            ){
                Image(
                    painter = painterResource(id = R.drawable.rockrigth),
                    contentDescription = "", // decorative element
                    alignment = Alignment.Center,
                    modifier = Modifier.size(90.dp)
                )
            }

            Box (
                modifier = Modifier.clickable {
                    playerPick.value = R.drawable.paperrigth
                    computerPick.value = computerPicker()
                    result.value = CheckResult(playerPick.value, computerPick.value)
                    viewModel.insertGame(getResult(result.value,playerPick.value, computerPick.value))
                }

            ) {
                Image(
                    painter = painterResource(id = R.drawable.paperrigth),
                    contentDescription = "", // decorative element
                    alignment = Alignment.Center,
                    modifier = Modifier.size(90.dp)
                )

            }

            Box (
                modifier = Modifier.clickable {
                    playerPick.value = R.drawable.scissorsrigth
                    computerPick.value = computerPicker()
                    result.value = CheckResult(playerPick.value, computerPick.value)
                    viewModel.insertGame(getResult(result.value,playerPick.value, computerPick.value))
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.scissorsrigth),
                    contentDescription = "", // decorative element
                    alignment = Alignment.Center,
                    modifier = Modifier.size(90.dp)
                )
            }
        }
    }
}


fun computerPicker (): Int{
    val picker = (1..3).random()

    if (picker == 1){
        return R.drawable.scissorsrigth
    }
    else if(picker == 2){
        return R.drawable.paperrigth
    }
    else if(picker == 3){
        return R.drawable.rockrigth
    }

    return 0
}

fun CheckResult (playerAns : Int, computerAns : Int): String{
    if (playerAns == computerAns){
        return "tie"
    }
    if (playerAns == R.drawable.scissorsrigth && computerAns == R.drawable.paperrigth
        || playerAns == R.drawable.paperrigth && computerAns == R.drawable.rockrigth
        || playerAns == R.drawable.rockrigth && computerAns == R.drawable.scissorsrigth){
        return "win"
    }
    else{
        return "lose"
    }
    return ""
}

fun getResult(result : String, playerAns : Int, computerAns : Int) : Results{
    var date = Date()
    return Results(result, date, computerAns, playerAns)
}