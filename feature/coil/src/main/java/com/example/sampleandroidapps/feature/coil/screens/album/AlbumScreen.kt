package com.example.sampleandroidapps.feature.coil.screens.album

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleandroidapps.feature.coil.navigation.CoilAppNavKey
import com.example.sampleandroidapps.feature.coil.screens.album.section.AlbumItemSection
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme

@Composable
fun AlbumScreen(
    onNavigate: (CoilAppNavKey) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AlbumViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    AlbumScreen(uiState, onNavigate, modifier)
}

@Composable
private fun AlbumScreen(
    uiState: AlbumScreenUiState,
    onNavigate: (CoilAppNavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(uiState.albumList) { album ->
            AlbumItemSection(
                album,
                modifier = Modifier.clickable {
                    onNavigate(CoilAppNavKey.PhotoScreen(album))
                }
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAlbumScreen() {
    SampleAndroidAppsTheme {
        AlbumScreen(
            AlbumScreenUiState(
                listOf(
                    Album(1, 1, "title1"),
                    Album(2, 2, "title2"),
                    Album(3, 3, "title3")
                )
            ),
            {}
        )
    }
}
