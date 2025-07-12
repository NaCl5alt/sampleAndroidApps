package com.example.sampleandroidapps.coil.screens.photo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleandroidapps.coil.navigation.CoilAppNavKey
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
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Photo Screen")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "AlbumId: ${uiState.album.id}")

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                onNavigate(
                    CoilAppNavKey.PhotoDetailScreen(
                        Photo(
                            albumId = 1,
                            id = 1,
                            title = "title",
                            url = "",
                            thumbnailUrl = ""
                        )
                    )
                )
            }
        ) {
            Text("Go to PhotoDetailScreen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoScreen() {
    SampleAndroidAppsTheme {
        PhotoScreen(uiState = PhotoScreenUiState.default, onNavigate = {})
    }
}
