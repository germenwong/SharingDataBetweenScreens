package com.hgm.sharingdata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hgm.sharingdata.sample.AppRoot
import com.hgm.sharingdata.sample.NavigationArgsSample
import com.hgm.sharingdata.sample.PersistentStorageSample
import com.hgm.sharingdata.sample.SharedViewModelSample
import com.hgm.sharingdata.sample.StatefulDependencySample
import com.hgm.sharingdata.ui.theme.SharingDataBetweenScreensTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                  SharingDataBetweenScreensTheme {
                        Column(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
                              PersistentStorageSample()
                        }
                  }
            }
      }
}
