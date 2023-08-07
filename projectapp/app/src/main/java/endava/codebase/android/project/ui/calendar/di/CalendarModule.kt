package endava.codebase.android.project.ui.calendar.di

import endava.codebase.android.project.ui.calendar.CalendarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calendarModule = module {
    viewModel {
        CalendarViewModel(
            dateRepository = get(),
        )
    }
}
