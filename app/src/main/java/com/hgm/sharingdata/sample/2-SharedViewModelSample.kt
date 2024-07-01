package com.hgm.sharingdata.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.hgm.sharingdata.sample.util.SharedViewModel

/**
 * 屏幕之间共享 ViewModel
 **/
@Composable
fun SharedViewModelSample() {
      val navController = rememberNavController()
      NavHost(
            navController = navController,
            startDestination = "onboarding"
      ) {
            navigation(
                  route = "onboarding",
                  startDestination = "personal_details"
            ) {
                  composable("personal_details") { entry ->
                        val viewModel = entry.sharedViewModel<SharedViewModel>(navController,)
                        val state by viewModel.sharedState.collectAsStateWithLifecycle()

                        PersonalDetailsScreen(
                              sharedState = state,
                              onNavigate = {
                                    viewModel.updateState()
                                    navController.navigate("terms_and_conditions")
                              }
                        )
                  }
                  composable("terms_and_conditions") { entry ->
                        val viewModel = entry.sharedViewModel<SharedViewModel>(navController)
                        val state by viewModel.sharedState.collectAsStateWithLifecycle()

                        TermsAndConditionsScreen(
                              sharedState = state,
                              onOnboardingFinished = {
                                    navController.navigate(route = "other_screen") {
                                          popUpTo("onboarding") {
                                                inclusive = true
                                          }
                                    }
                              }
                        )
                  }
            }
            composable("other_screen") {
                  Text(text = "Hello world")
            }
      }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
      navController: NavHostController,
): T {
      val navGraphRoute = destination.parent?.route ?: return viewModel()
      val parentEntry = remember(this) {
            navController.getBackStackEntry(navGraphRoute)
      }

      // 如果使用 Hilt 可替换成 hiltViewModel
      return viewModel(parentEntry)
}

@Composable
private fun PersonalDetailsScreen(
      sharedState: Int,
      onNavigate: () -> Unit
) {
      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
            Button(onClick = onNavigate) {
                  Text(text = "Click me")
            }
      }
}

@Composable
private fun TermsAndConditionsScreen(
      sharedState: Int,
      onOnboardingFinished: () -> Unit
) {
      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
            Button(onClick = onOnboardingFinished) {
                  Text(text = "State: $sharedState")
            }
      }
}