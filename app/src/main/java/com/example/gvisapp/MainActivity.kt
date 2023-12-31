package com.example.gvisapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.health.connect.client.HealthConnectClient
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gvisapp.data.HealthConnectManager
import com.example.gvisapp.food.AddFoodScreen
import com.example.gvisapp.food.AddNewFoodScreen
import com.example.gvisapp.food.FoodScreen
import com.example.gvisapp.login.LoginScreen
import com.example.gvisapp.ui.theme.GvisAppTheme

class MainActivity : ComponentActivity() {
    companion object {
        var isTextFieldFocused = false
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (isTextFieldFocused) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            currentFocus!!.clearFocus()
        }

        return try {
            super.dispatchTouchEvent(ev)
        } catch (e: Exception) {
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val healthConnectManager = HealthConnectManager(LocalContext.current)
            GvisAppTheme {
                val activity = LocalContext.current
                val settingsIntent = Intent()
                settingsIntent.action =
                    HealthConnectClient.ACTION_HEALTH_CONNECT_SETTINGS
                activity.startActivity(settingsIntent)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val bottomValue = remember {
                        mutableIntStateOf(0)
                    }
                    NavHost(navController, "LOGIN") {
                        composable("LOGIN") {
                            LoginScreen(navController,healthConnectManager)
                        }
                        composable("SCREEN") {
                            MainScreen(bottomValue, navController)
                        }
                        composable("Food/{where}") {
                            FoodScreen(
                                bottomValue,
                                it.arguments?.getString("where")!!.toInt(),
                                navController
                            )
                        }
                        composable("AddFood/{where}") {
                            AddFoodScreen(
                                bottomValue,
                                it.arguments?.getString("where")!!.toInt(),
                                navController
                            )
                        }
                        composable("AddNewFood/{where}") {
                            AddNewFoodScreen(
                                bottomValue,
                                it.arguments?.getString("where")!!.toInt(),
                                navController
                            )
                        }
                    }
                }
            }
        }
    }
}