package com.keremsen.wordmasters.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.keremsen.wordmasters.view.LoginScreen
import com.keremsen.wordmasters.view.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login_screen"
    ) {

        composable("login_screen") {
            LoginScreen(navController)
        }
        composable(
            route = "main/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->

            val userId = backStackEntry.arguments?.getInt("userId")!!.toInt()
            MainScreen( navController ,userId)
        }
    }
}


