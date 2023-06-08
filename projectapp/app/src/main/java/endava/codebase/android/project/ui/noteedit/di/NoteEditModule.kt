package endava.codebase.android.project.ui.noteedit.di

import endava.codebase.android.project.ui.noteedit.NoteEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteEditModule = module {
    viewModel { parameters ->
        NoteEditViewModel(
            noteRepository = get(),
            id = parameters.get(),
        )
    }
}
