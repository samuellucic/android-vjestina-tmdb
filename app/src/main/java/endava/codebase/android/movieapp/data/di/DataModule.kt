package endava.codebase.android.movieapp.data.di

import endava.codebase.android.movieapp.data.database.MovieAppDatabase
import endava.codebase.android.movieapp.data.repository.MovieRepository
import endava.codebase.android.movieapp.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            movieService = get(),
            movieDao = get<MovieAppDatabase>().favoriteMovieDao(),
            bgDispatcher = Dispatchers.IO
        )
    }
}
