package com.example.sampleandroidapps.coil.screens.photoDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampleandroidapps.coil.screens.photoDetails.compose.PhotoView
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@Composable
fun PhotoDetailScreen(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        PhotoView(photo.url)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.4F))
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = photo.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoDetailScreen() {
    SampleAndroidAppsTheme {
        PhotoDetailScreen(
            photo = Photo(
                albumId = 1,
                id = 1,
                title = "title",
                url = "https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg",
                thumbnailUrl = "https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg"
            )
        )
    }
}
