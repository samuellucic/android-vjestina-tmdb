package endava.codebase.android.project.data.di

import endava.codebase.android.project.data.database.ProjectAppDatabase
import endava.codebase.android.project.data.repository.date.DateRepository
import endava.codebase.android.project.data.repository.date.DateRepositoryImpl
import endava.codebase.android.project.data.repository.note.NoteRepository
import endava.codebase.android.project.data.repository.note.NoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<NoteRepository> {
        NoteRepositoryImpl(
            noteDao = get<ProjectAppDatabase>().noteDao(),
            bgDispatcher = Dispatchers.IO,
        )
    }

    single<DateRepository> {
        DateRepositoryImpl(
            dateDao = get<ProjectAppDatabase>().dateDao(),
            bgDispatcher = Dispatchers.IO,
        )
    }
}
