package endava.codebase.android.project.ui.notes.di

import endava.codebase.android.project.ui.notes.NotesViewModel
import endava.codebase.android.project.ui.notes.mapper.NotesMapper
import endava.codebase.android.project.ui.notes.mapper.NotesMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule = module {
    viewModel { parameters ->
        NotesViewModel(
            noteRepository = get(),
            notesMapper = get(),
            dateId = parameters.get()
        )
    }

    single<NotesMapper> { NotesMapperImpl() }
}
