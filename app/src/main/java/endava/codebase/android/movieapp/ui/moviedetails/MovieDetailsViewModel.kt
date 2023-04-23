package endava.codebase.android.movieapp.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.movieapp.data.repository.MovieRepository
import endava.codebase.android.movieapp.mock.MoviesMock
import endava.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    val movieDetailsMapper: MovieDetailsMapper,
    val movieId: Int
) : ViewModel() {
    init {
        generateMovieDetails()
    }

    private val _movieDetailsViewState = MutableStateFlow(
        movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())
    )
    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        _movieDetailsViewState.asStateFlow()

    fun toggleFavorite() {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

    private fun generateMovieDetails() {
        viewModelScope.launch {
            movieRepository.movieDetails(movieId)
                .collect { movieDetails ->
                    _movieDetailsViewState.value =
                        movieDetailsMapper.toMovieDetailsViewState(movieDetails)
                }
        }
    }
}
