package endava.codebase.android.movieapp.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.movieapp.data.repository.MovieRepository
import endava.codebase.android.movieapp.model.Movie
import endava.codebase.android.movieapp.model.MovieDetails
import endava.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    val movieDetailsMapper: MovieDetailsMapper,
    val movieId: Int
) : ViewModel() {

    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        movieRepository.movieDetails(movieId)
            .map { movieDetails ->
                movieDetailsMapper.toMovieDetailsViewState(movieDetails)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = movieDetailsMapper.toMovieDetailsViewState(
                    MovieDetails(
                        movie = Movie(
                            id = 1,
                            title = "",
                            overview = "",
                            imageUrl = null,
                            isFavorite = false,
                        ),
                        voteAverage = 0f,
                        releaseDate = "",
                        language = "",
                        runtime = 0,
                        cast = listOf(),
                        crew = listOf(),
                    )
                ),
            )

    fun toggleFavorite() {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
