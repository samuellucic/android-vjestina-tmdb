package endava.codebase.android.movieapp.ui.home.di

import endava.codebase.android.movieapp.data.di.dataModule
import endava.codebase.android.movieapp.ui.home.HomeViewModel
import endava.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import endava.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    includes(dataModule)

    viewModel {
        HomeViewModel(
            movieRepository = get(),
            homeMapper = get(),
        )
    }

    single<HomeScreenMapper> { HomeScreenMapperImpl() }
}
