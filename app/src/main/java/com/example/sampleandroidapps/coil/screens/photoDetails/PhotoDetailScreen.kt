package com.example.sampleandroidapps.coil.screens.photoDetails

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@Composable
fun PhotoDetailScreen(modifier: Modifier = Modifier) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Photo Detail Screen")

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { dispatcher?.onBackPressed() }) {
            Text("back")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoDetailScreen() {
    SampleAndroidAppsTheme {
        PhotoDetailScreen()
    }
}
