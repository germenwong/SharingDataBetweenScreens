package com.hgm.sharingdata.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgm.sharingdata.sample.util.PersistentViewModel1
import com.hgm.sharingdata.sample.util.PersistentViewModel2

@Composable
fun PersistentStorageSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            val viewModel = hiltViewModel<PersistentViewModel1>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            Screen1(
                onNavigateToScreen2 = {
                    viewModel.saveSession()
                    navController.navigate("screen2")
                }
            )
        }
        composable(
            route = "screen2"
        ) {
            val viewModel = hiltViewModel<PersistentViewModel2>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            Screen2()
        }
    }
}

@Composable
private fun Screen1(
    onNavigateToScreen2: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            onNavigateToScreen2()
        }) {
            Text(text = "Go to next screen")
        }
    }
}

@Composable
private fun Screen2() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello world")
    }
}