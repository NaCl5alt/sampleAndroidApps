package com.example.sampleandroidapps.coil.screens.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleandroidapps.network.jsonPlaceholder.album.Album
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.Photo
import com.example.sampleandroidapps.network.jsonPlaceholder.photo.usecase.GetPhotoListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class PhotoScreenUiState(
    val album: Album,
    val photoList: List<Photo>
) {
    companion object {
        val default = PhotoScreenUiState(
            album = Album(
                userId = 0,
                id = 0,
                title = ""
            ),
            photoList = emptyList()
        )
    }
}

@HiltViewModel(assistedFactory = PhotoViewModel.Factory::class)
class PhotoViewModel @AssistedInject constructor(
    private val getPhotoListUseCase: GetPhotoListUseCase,
    @Assisted
    private val album: Album
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        PhotoScreenUiState.default.copy(album = album)
    )
    val uiState: StateFlow<PhotoScreenUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotoListUseCase(album.id).onSuccess { photoList ->
                withContext(Dispatchers.Main) {
                    _uiState.update { state ->
                        _uiState.value.copy(photoList = photoList)
                    }
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            album: Album
        ): PhotoViewModel
    }
}
