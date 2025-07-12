package com.example.sampleandroidapps.coil.screens.photo.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampleandroidapps.coil.screens.photo.compose.ThumbnailIcon
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@Composable
fun PhotoItemSection(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        )

        Row {
            ThumbnailIcon(thumbnailUrl = photo.thumbnailUrl)

            Spacer(Modifier.width(8.dp))

            Text(
                text = photo.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoItemSection() {
    SampleAndroidAppsTheme {
        PhotoItemSection(
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