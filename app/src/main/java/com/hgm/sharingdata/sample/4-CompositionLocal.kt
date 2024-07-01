package com.hgm.sharingdata.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hgm.sharingdata.ui.theme.SharingDataBetweenScreensTheme
import kotlinx.coroutines.launch

/**
 * 全局状态共享
 **/
val LocalSnackbarHostState = compositionLocalOf {
      SnackbarHostState()
}

@Composable
fun AppRoot() {
      val snackbarHostState = LocalSnackbarHostState.current
      CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
            Scaffold(
                  snackbarHost = {
                        SnackbarHost(hostState = LocalSnackbarHostState.current)
                  }
            ) { padding ->
                  Box(modifier = Modifier.padding(padding)) {
                        MyScreen()
                  }
            }
      }
}

@Composable
private fun MyScreen() {
      val snackbarHostState = LocalSnackbarHostState.current
      val scope = rememberCoroutineScope()
      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
            Button(onClick = {
                  scope.launch {
                        snackbarHostState.showSnackbar("Hello world!")
                  }
            }) {
                  Text(text = "Show snackbar")
            }
      }
}

@Preview
@Composable
fun MyScreenPreview() {
      SharingDataBetweenScreensTheme {
            MyScreen()
      }
}