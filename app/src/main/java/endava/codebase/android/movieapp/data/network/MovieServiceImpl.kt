package endava.codebase.android.movieapp.data.network

import endava.codebase.android.movieapp.data.network.model.ApiMovieDetails
import endava.codebase.android.movieapp.data.network.model.MovieCreditsResponse
import endava.codebase.android.movieapp.data.network.model.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
private const val BASE_URL = "https://api.themoviedb.org/3"
private const val API_KEY = "da8a17106e7ca013dd7b54ed7a3a10f2"

class MovieServiceImpl(private val client: HttpClient) : MovieService {

    private suspend fun getResponse(urlString: String): HttpResponse {
        return client.get(urlString) {
            parameter("api_key", API_KEY)
        }
    }

    override suspend fun fetchPopularMovies(): MovieResponse {
        return getResponse("$BASE_URL/movie/popular").body()
    }

    override suspend fun fetchTopRatedMovies(): MovieResponse {
        return getResponse("$BASE_URL/movie/top_rated").body()
    }

    override suspend fun fetchNowPlayingMovies(): MovieResponse {
        return getResponse("$BASE_URL/movie/now_playing").body()
    }

    override suspend fun fetchUpcomingMovies(): MovieResponse {
        return getResponse("$BASE_URL/movie/upcoming").body()
    }

    override suspend fun fetchMovieDetails(movieId: Int): ApiMovieDetails {
        return getResponse("$BASE_URL/movie/$movieId").body()
    }

    override suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse {
        return getResponse("$BASE_URL/movie/$movieId/credits").body()
    }
}
