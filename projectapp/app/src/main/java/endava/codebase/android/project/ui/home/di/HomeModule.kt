package endava.codebase.android.project.ui.home.di

import endava.codebase.android.project.ui.home.HomeViewModel
import endava.codebase.android.project.ui.home.mapper.HomeMapper
import endava.codebase.android.project.ui.home.mapper.HomeMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            dateRepository = get(),
            homeMapper = get(),
        )
    }

    single<HomeMapper> { HomeMapperImpl() }
}
