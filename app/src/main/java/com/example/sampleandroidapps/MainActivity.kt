package com.example.sampleandroidapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sampleandroidapps.feature.coil.CoilApp
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleAndroidAppsTheme {
                CoilApp()
            }
        }
    }
}
