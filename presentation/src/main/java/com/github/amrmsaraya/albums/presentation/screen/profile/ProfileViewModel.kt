package com.github.amrmsaraya.albums.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amrmsaraya.albums.common.di.MainDispatcher
import com.github.amrmsaraya.albums.domain.usecase.GetAlbumsUseCase
import com.github.amrmsaraya.albums.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val getUsersUseCase: GetUsersUseCase,
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private val intentFlow = MutableSharedFlow<ProfileIntent>()

    init {
        handleIntent()
        getUsers()
    }

    fun sendIntent(intent: ProfileIntent) = viewModelScope.launch(dispatcher) {
        intentFlow.emit(intent)
    }

    private fun handleIntent() = viewModelScope.launch(dispatcher) {
        intentFlow.collect { intent ->
            when (intent) {
                is ProfileIntent.GetAlbums -> getAlbums(intent.id)
                ProfileIntent.GetUsers -> getUsers()
                ProfileIntent.ResetError -> resetError()
            }
        }
    }

    private fun getUsers() = viewModelScope.launch(dispatcher) {
        _state.update { it.copy(isUsersLoading = true, error = null) }
        getUsersUseCase()
            .onSuccess { users ->
                val randomUser = users.randomOrNull()
                _state.update { it.copy(user = randomUser, isUsersLoading = false) }
                randomUser?.let { getAlbums(it.id) }
            }
            .onFailure { throwable ->
                _state.update { it.copy(error = throwable.message, isUsersLoading = false) }
            }
    }

    private fun getAlbums(id: Int) = viewModelScope.launch(dispatcher) {
        _state.update { it.copy(isAlbumsLoading = true, error = null) }
        getAlbumsUseCase(id)
            .onSuccess { albums ->
                _state.update { it.copy(albums = albums, isAlbumsLoading = false) }
            }
            .onFailure { throwable ->
                _state.update { it.copy(error = throwable.message, isAlbumsLoading = false) }
            }
    }

    private fun resetError() = _state.update { it.copy(error = null) }
}