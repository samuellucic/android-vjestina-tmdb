package endava.codebase.android.project.ui.notes.mapper

import endava.codebase.android.project.model.Note
import endava.codebase.android.project.ui.notes.NotesViewState

interface NotesMapper {
    fun toNotesViewState(notes: List<Note>, dateId: Int): NotesViewState
}
