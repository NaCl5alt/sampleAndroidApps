package com.example.sampleandroidapps.coil.screens.photo

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
import com.example.sampleandroidapps.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@Composable
fun PhotoScreen(modifier: Modifier = Modifier, onNavigate: (CoilAppNavKey) -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Photo Screen")

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { onNavigate(CoilAppNavKey.PhotoDetailScreen) }) {
            Text("Go to PhotoDetailScreen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoScreen() {
    SampleAndroidAppsTheme {
        PhotoScreen {}
    }
}
