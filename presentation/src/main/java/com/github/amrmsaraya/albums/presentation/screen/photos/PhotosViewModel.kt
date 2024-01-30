package com.github.amrmsaraya.albums.presentation.screen.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amrmsaraya.albums.common.di.MainDispatcher
import com.github.amrmsaraya.albums.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PhotosState())
    val state = _state.asStateFlow()

    private val intentFlow = MutableSharedFlow<PhotosIntent>()

    init {
        handleIntent()
    }

    fun sendIntent(intent: PhotosIntent) = viewModelScope.launch(dispatcher) {
        intentFlow.emit(intent)
    }

    private fun handleIntent() = viewModelScope.launch(dispatcher) {
        intentFlow.collect { intent ->
            when (intent) {
                PhotosIntent.ResetError -> resetError()
                is PhotosIntent.GetPhotos -> getPhotos(intent.albumId)
            }
        }
    }

    private fun getPhotos(albumId: Int) = viewModelScope.launch(dispatcher) {
        _state.update { it.copy(isLoading = true, error = null) }
        getPhotosUseCase(albumId)
            .onSuccess { photos ->
                _state.update { it.copy(photos = photos, isLoading = false) }
            }
            .onFailure { throwable ->
                _state.update { it.copy(error = throwable.message, isLoading = false) }
            }
    }

    private fun resetError() = _state.update { it.copy(error = null) }
}