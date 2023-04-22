package endava.codebase.android.movieapp.ui.favorites.mapper

import endava.codebase.android.movieapp.model.Movie
import endava.codebase.android.movieapp.ui.component.MovieCardViewState
import endava.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import endava.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        val favoriteMovieViewStates = ArrayList<FavoritesMovieViewState>()

        favoriteMovies.forEach { favoriteMovie ->
            favoriteMovieViewStates.add(
                FavoritesMovieViewState(
                    id = favoriteMovie.id,
                    movieCardViewState = MovieCardViewState(
                        favoriteMovie.imageUrl.toString(),
                        favoriteMovie.isFavorite,
                    ),
                )
            )
        }

        return FavoritesViewState(favoriteMovieViewStates)
    }
}
