package endava.codebase.android.movieapp.data.network.model

import endava.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import endava.codebase.android.movieapp.model.Movie
import endava.codebase.android.movieapp.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("vote_average")
    val voteAverage: Float,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("runtime")
    val runtime: Int
) {
    fun toMovieDetails(
        isFavorite: Boolean,
        crew: List<ApiCrew>,
        cast: List<ApiCast>,
    ): MovieDetails {
        return MovieDetails(
            movie = Movie(
                id = id,
                title = title,
                overview = overview,
                imageUrl = "$BASE_IMAGE_URL/$posterPath",
                isFavorite = isFavorite,
            ),
            voteAverage = voteAverage / 10,
            releaseDate = releaseDate.toString(),
            language = originalLanguage,
            runtime = runtime,
            crew = crew.map { apiCrew ->
                apiCrew.toCrew()
            },
            cast = cast.map { apiCast ->
                apiCast.toActor()
            },
        )
    }
}
