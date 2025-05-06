package com.keremsen.wordmasters.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController,id :Int){
    Surface(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Red)

    ) {
        Text("Main Page")
    }
}