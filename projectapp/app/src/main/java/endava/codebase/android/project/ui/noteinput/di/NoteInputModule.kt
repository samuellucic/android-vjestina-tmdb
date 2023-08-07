package endava.codebase.android.project.ui.noteinput.di

import endava.codebase.android.project.ui.noteinput.NoteInputViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteInputModule = module {
    viewModel {
        NoteInputViewModel(
            noteRepository = get(),
            dateId = get(),
        )
    }
}
