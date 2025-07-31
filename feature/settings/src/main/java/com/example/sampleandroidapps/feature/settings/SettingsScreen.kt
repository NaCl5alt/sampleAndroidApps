package com.example.sampleandroidapps.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sampleandroidapps.feature.settings.section.LicensesItemSection
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                LicensesItemSection()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
    SampleAndroidAppsTheme {
        SettingsScreen()
    }
}
