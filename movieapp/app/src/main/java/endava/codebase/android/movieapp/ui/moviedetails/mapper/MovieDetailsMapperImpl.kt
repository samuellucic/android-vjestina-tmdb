package endava.codebase.android.movieapp.ui.moviedetails.mapper

import endava.codebase.android.movieapp.model.MovieDetails
import endava.codebase.android.movieapp.ui.moviedetails.ActorViewState
import endava.codebase.android.movieapp.ui.moviedetails.CrewmanViewState
import endava.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl.toString(),
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = movieDetails.crew.map { crewman ->
                CrewmanViewState(
                    id = crewman.id,
                    name = crewman.name,
                    job = crewman.job,
                )
            },
            cast = movieDetails.cast.map { actor ->
                ActorViewState(
                    id = actor.id,
                    imageUrl = actor.imageUrl.toString(),
                    name = actor.name,
                    character = actor.character,
                )
            },
        )
    }
}
