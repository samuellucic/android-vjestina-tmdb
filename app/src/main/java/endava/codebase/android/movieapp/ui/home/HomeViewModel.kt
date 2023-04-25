package endava.codebase.android.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.movieapp.data.repository.MovieRepository
import endava.codebase.android.movieapp.model.Movie
import endava.codebase.android.movieapp.model.MovieCategory
import endava.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    val homeMapper: HomeScreenMapper,
) : ViewModel() {
    private val _homeTrendingViewState: MutableStateFlow<HomeMovieCategoryViewState> =
        MutableStateFlow(
            HomeMovieCategoryViewState(listOf(), listOf())
        )
    private val _homeNewReleasesViewState: MutableStateFlow<HomeMovieCategoryViewState> =
        MutableStateFlow(
            HomeMovieCategoryViewState(listOf(), listOf())
        )

    val homeTrendingViewState: StateFlow<HomeMovieCategoryViewState> =
        _homeTrendingViewState.asStateFlow()
    val homeNewReleasesViewState: StateFlow<HomeMovieCategoryViewState> =
        _homeNewReleasesViewState.asStateFlow()

    init {
        generateCategory(
            homeViewState = _homeTrendingViewState,
            moviesFlow = movieRepository.trendingMovies(MovieCategory.POPULAR),
            movieCategory = MovieCategory.POPULAR,
            movieCategories = listOf(MovieCategory.POPULAR, MovieCategory.TOP_RATED)
        )
        generateCategory(
            homeViewState = _homeNewReleasesViewState,
            moviesFlow = movieRepository.newReleases(MovieCategory.NOW_PLAYING),
            movieCategory = MovieCategory.NOW_PLAYING,
            movieCategories = listOf(MovieCategory.NOW_PLAYING, MovieCategory.UPCOMING)
        )
    }

    fun changeCategory(categoryId: Int) {
        viewModelScope.launch {
            val homeViewState: MutableStateFlow<HomeMovieCategoryViewState>
            val moviesFlow: Flow<List<Movie>>
            val movieCategories: List<MovieCategory>
            val movieCategory = MovieCategory.values()[categoryId]

            when (movieCategory) {
                MovieCategory.POPULAR, MovieCategory.TOP_RATED -> {
                    homeViewState = _homeTrendingViewState
                    moviesFlow = movieRepository.trendingMovies(movieCategory)
                    movieCategories = listOf(MovieCategory.POPULAR, MovieCategory.TOP_RATED)
                }
                MovieCategory.NOW_PLAYING, MovieCategory.UPCOMING -> {
                    homeViewState = _homeNewReleasesViewState
                    moviesFlow = movieRepository.newReleases(movieCategory)
                    movieCategories = listOf(MovieCategory.NOW_PLAYING, MovieCategory.UPCOMING)
                }
            }
            generateCategory(
                homeViewState = homeViewState,
                moviesFlow = moviesFlow,
                movieCategories = movieCategories,
                movieCategory = movieCategory,
            )
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

    private fun generateCategory(
        homeViewState: MutableStateFlow<HomeMovieCategoryViewState>,
        moviesFlow: Flow<List<Movie>>,
        movieCategories: List<MovieCategory>,
        movieCategory: MovieCategory,
    ) {
        viewModelScope.launch {
            moviesFlow.collect { movies ->
                homeViewState.value =
                    homeMapper.toHomeMovieCategoryViewState(
                        movieCategories = movieCategories,
                        selectedMovieCategory = movieCategory,
                        movies = movies
                    )
            }
        }
    }
}
