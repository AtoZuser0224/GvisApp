package com.example.gvisapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.gvisapp.Food.card.FoodCard
import com.example.gvisapp.composable.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(bottomValue: MutableState<Int>,navController: NavController){

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CHAT FIT")
                },
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(Icons.Default.Menu, contentDescription = null,Modifier.fillMaxSize())
                    }
                }
            )
        },
        bottomBar = {
            BottomNavBar(bottomValue,navController = navController)
        },
    ){ innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Divider(Modifier.fillMaxWidth())
            FoodCard(navController)
        }
    }
}
