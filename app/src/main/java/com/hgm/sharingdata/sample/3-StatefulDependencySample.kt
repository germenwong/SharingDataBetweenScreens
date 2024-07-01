package com.hgm.sharingdata.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgm.sharingdata.sample.util.Screen1ViewModel

/**
 * 状态依赖示例
 **/
@Composable
fun StatefulDependencySample() {
      val navController = rememberNavController()
      NavHost(
            navController = navController,
            startDestination = "screen1"
      ) {
            composable("screen1") {
                  val viewModel = hiltViewModel<Screen1ViewModel>()
                  val count by viewModel.count.collectAsStateWithLifecycle()

                  Screen1(
                        count = count,
                        onNavigateToScreen2 = {
                              viewModel.inc()
                              navController.navigate("screen2")
                        }
                  )
            }
            composable(
                  route = "screen2"
            ) {
                  val viewModel = hiltViewModel<Screen1ViewModel>()
                  val count by viewModel.count.collectAsStateWithLifecycle()

                  Screen2(count)
            }
      }
}

@Composable
private fun Screen1(
      count: Int,
      onNavigateToScreen2: () -> Unit
) {
      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
            Button(onClick = {
                  onNavigateToScreen2()
            }) {
                  Text(text = "Count on screen1: $count")
            }
      }
}

@Composable
private fun Screen2(count: Int) {
      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
            Text(text = "Count on screen2: $count")
      }
}