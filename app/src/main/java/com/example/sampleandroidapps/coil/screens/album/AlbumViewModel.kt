package com.example.sampleandroidapps.coil.screens.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.album.usecase.GetAlbumListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class AlbumScreenUiState(
    val albumList: List<Album> = emptyList()
) {
    companion object {
        val default = AlbumScreenUiState()
    }
}

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumListUseCase: GetAlbumListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AlbumScreenUiState.default)
    val uiState: StateFlow<AlbumScreenUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAlbumListUseCase().onSuccess { albumList ->
                withContext(Dispatchers.Main) {
                    _uiState.update { state ->
                        state.copy(albumList = albumList)
                    }
                }
            }
        }
    }
}
