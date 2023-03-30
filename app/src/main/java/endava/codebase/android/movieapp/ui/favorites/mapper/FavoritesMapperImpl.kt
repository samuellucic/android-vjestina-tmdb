package endava.codebase.android.movieapp.ui.favorites.mapper

import endava.codebase.android.movieapp.model.Movie
import endava.codebase.android.movieapp.ui.component.MovieCardViewState
import endava.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import endava.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        return FavoritesViewState(
            favoriteMovies = favoriteMovies
                .asSequence()
                .map { favoriteMovie ->
                    FavoritesMovieViewState(
                        id = favoriteMovie.id,
                        movieCardViewState = MovieCardViewState(
                            favoriteMovie.imageUrl.toString(),
                            favoriteMovie.isFavorite,
                        ),
                    )
                }
                .toList()
        )
    }

}
