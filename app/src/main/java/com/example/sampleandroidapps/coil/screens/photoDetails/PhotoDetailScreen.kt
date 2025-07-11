package com.example.sampleandroidapps.coil.screens.photoDetails

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@Composable
fun PhotoDetailScreen(modifier: Modifier = Modifier) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Photo Detail Screen")

        Spacer(modifier = Modifier.height(16.dp))

        val painter = rememberAsyncImagePainter("https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg")
        val state by painter.state.collectAsState()
        val retryLoadImage = { painter.restart() }

        when (state) {
            is AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Gray)
                ) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }

            is AsyncImagePainter.State.Success -> {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            is AsyncImagePainter.State.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Gray)
                        .clickable { retryLoadImage() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null)
                }
            }
        }

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
