package endava.codebase.android.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.movieapp.data.repository.MovieRepository
import endava.codebase.android.movieapp.model.MovieCategory
import endava.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    val homeMapper: HomeScreenMapper,
) : ViewModel() {
    private val trendingHomeCategorySelected = MutableStateFlow(MovieCategory.POPULAR)
    private val newReleasesHomeCategorySelected = MutableStateFlow(MovieCategory.NOW_PLAYING)

    val homeTrendingViewState: StateFlow<HomeMovieCategoryViewState> =
        trendingHomeCategorySelected
            .flatMapLatest { category ->
                movieRepository.movies(category)
                    .map { movies ->
                        homeMapper.toHomeMovieCategoryViewState(
                            movieCategories = listOf(
                                MovieCategory.POPULAR,
                                MovieCategory.TOP_RATED
                            ),
                            selectedMovieCategory = category,
                            movies = movies
                        )
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = HomeMovieCategoryViewState(listOf(), listOf())
            )
    val homeNewReleasesViewState: StateFlow<HomeMovieCategoryViewState> =
        newReleasesHomeCategorySelected
            .flatMapLatest { category ->
                movieRepository.movies(category)
                    .map { movies ->
                        homeMapper.toHomeMovieCategoryViewState(
                            movieCategories = listOf(
                                MovieCategory.NOW_PLAYING,
                                MovieCategory.UPCOMING
                            ),
                            selectedMovieCategory = category,
                            movies = movies
                        )
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = HomeMovieCategoryViewState(listOf(), listOf())
            )

    fun changeCategory(categoryId: Int) {
        when (val movieCategory = MovieCategory.values()[categoryId]) {
            MovieCategory.POPULAR, MovieCategory.TOP_RATED -> {
                trendingHomeCategorySelected.value = movieCategory
            }
            MovieCategory.NOW_PLAYING, MovieCategory.UPCOMING -> {
                newReleasesHomeCategorySelected.value = movieCategory
            }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
