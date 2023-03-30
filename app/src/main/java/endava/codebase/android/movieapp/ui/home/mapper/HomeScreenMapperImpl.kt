package endava.codebase.android.movieapp.ui.home.mapper

import android.content.res.Resources
import endava.codebase.android.movieapp.R
import endava.codebase.android.movieapp.model.Movie
import endava.codebase.android.movieapp.model.MovieCategory
import endava.codebase.android.movieapp.ui.component.MovieCardViewState
import endava.codebase.android.movieapp.ui.component.MovieCategoryLabelTextReferenceViewState
import endava.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import endava.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import endava.codebase.android.movieapp.ui.home.HomeMovieViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>
    ): HomeMovieCategoryViewState {
        return HomeMovieCategoryViewState(
            movieCategories = movieCategories.asSequence()
                .map { movieCategory ->
                    val resId = when (movieCategory) {
                        MovieCategory.POPULAR -> R.string.popular
                        MovieCategory.TOP_RATED -> R.string.top_rated
                        MovieCategory.NOW_PLAYING -> R.string.now_playing
                        MovieCategory.UPCOMING -> R.string.upcoming
                    }
                    MovieCategoryLabelViewState(
                        itemId = movieCategory.ordinal,
                        isSelected = selectedMovieCategory == movieCategory,
                        categoryText = MovieCategoryLabelTextReferenceViewState(resId),
                    )
                }
                .toList(),
            movies = movies.asSequence()
                .map { movie ->
                    HomeMovieViewState(
                        id = movie.id,
                        movieCardViewState = MovieCardViewState(
                            imageUrl = movie.imageUrl.toString(),
                            isFavorite = movie.isFavorite
                        )
                    )
                }
                .toList(),
        )
    }
}
