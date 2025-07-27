package com.example.sampleandroidapps.feature.coil.screens.photoDetails.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun PhotoView(photoUrl: String) {
    val painter = rememberAsyncImagePainter(photoUrl)
    val state by painter.state.collectAsState()
    val retryLoadImage = { painter.restart() }

    val zoomState = rememberZoomState()

    when (state) {
        is AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading -> {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        is AsyncImagePainter.State.Success -> {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .zoomable(zoomState),
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
}

@Preview
@Composable
private fun PreviewPhotoView() {
    SampleAndroidAppsTheme {
        PhotoView(
            photoUrl = "https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg"
        )
    }
}
