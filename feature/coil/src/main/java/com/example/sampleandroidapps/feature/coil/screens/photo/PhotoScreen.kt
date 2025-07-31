package com.example.sampleandroidapps.feature.coil.screens.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleandroidapps.feature.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.feature.coil.screens.photo.section.PhotoItemSection
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@Composable
fun PhotoScreen(
    onNavigate: (CoilAppNavKey) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PhotoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    PhotoScreen(uiState, onNavigate, modifier)
}

@Composable
private fun PhotoScreen(
    uiState: PhotoScreenUiState,
    onNavigate: (CoilAppNavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Text(
                    text = uiState.album.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }

            items(uiState.photoList) { photo ->
                PhotoItemSection(
                    photo, modifier = Modifier.clickable {
                        onNavigate(CoilAppNavKey.PhotoDetailScreen(photo))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoScreen() {
    SampleAndroidAppsTheme {
        PhotoScreen(
            uiState = PhotoScreenUiState.default.copy(
                album = PhotoScreenUiState.default.album.copy(title = "Album"),
                photoList = listOf(
                    Photo(
                        albumId = 1,
                        id = 1,
                        title = "title",
                        url = "https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg",
                        thumbnailUrl = "https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg"
                    ),
                    Photo(
                        albumId = 2,
                        id = 2,
                        title = "title2",
                        url = "https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg",
                        thumbnailUrl = "https://cdn2.thecatapi.com/images/MTgwMDcwNg.jpg"
                    )
                )
            ),
            onNavigate = {}
        )
    }
}
