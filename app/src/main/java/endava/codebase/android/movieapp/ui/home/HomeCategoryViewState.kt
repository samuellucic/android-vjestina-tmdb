package endava.codebase.android.movieapp.ui.home

import endava.codebase.android.movieapp.ui.component.MovieCardViewState
import endava.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState

data class HomeMovieViewState(
    val id: Int,
    val movieCardViewState: MovieCardViewState
)

data class HomeMovieCategoryViewState(
    val movieCategories: List<MovieCategoryLabelViewState>,
    val movies: List<HomeMovieViewState>,
)
