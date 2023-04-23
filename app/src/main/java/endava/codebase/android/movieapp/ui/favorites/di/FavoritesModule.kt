package endava.codebase.android.movieapp.ui.favorites.di

import endava.codebase.android.movieapp.data.di.dataModule
import endava.codebase.android.movieapp.ui.favorites.FavoritesViewModel
import endava.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import endava.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    includes(dataModule)

    viewModel {
        FavoritesViewModel(
            movieRepository = get(),
            favoritesMapper = get()
        )
    }

    single<FavoritesMapper> { FavoritesMapperImpl() }
}
