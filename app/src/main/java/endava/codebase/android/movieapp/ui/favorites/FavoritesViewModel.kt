package endava.codebase.android.movieapp.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.movieapp.data.repository.MovieRepository
import endava.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    val favoritesMapper: FavoritesMapper,
) : ViewModel() {
    init {
        generateFavorites()
    }

    private val _favoritesViewState: MutableStateFlow<FavoritesViewState> = MutableStateFlow(
        FavoritesViewState(listOf())
    )
    val favoritesViewState: StateFlow<FavoritesViewState> = _favoritesViewState.asStateFlow()

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

    private fun generateFavorites() {
        viewModelScope.launch {
            movieRepository.favoriteMovies()
                .collect { movies ->
                    _favoritesViewState.value = favoritesMapper.toFavoritesViewState(movies)
                }
        }
    }
}
