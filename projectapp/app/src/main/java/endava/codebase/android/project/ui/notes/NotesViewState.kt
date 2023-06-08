package endava.codebase.android.project.ui.notes

import endava.codebase.android.project.ui.component.NoteItemViewState

data class NoteViewState(
    val id: Int,
    val noteItemViewState: NoteItemViewState,
)

data class NotesViewState(
    val notes: List<NoteViewState>,
    val progress: Float,
    val dateId: Int,
)
