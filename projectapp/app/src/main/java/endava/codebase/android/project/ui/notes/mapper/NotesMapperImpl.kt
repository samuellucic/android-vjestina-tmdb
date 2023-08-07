package endava.codebase.android.project.ui.notes.mapper

import endava.codebase.android.project.model.Note
import endava.codebase.android.project.ui.component.NoteItemViewState
import endava.codebase.android.project.ui.notes.NoteViewState
import endava.codebase.android.project.ui.notes.NotesViewState

class NotesMapperImpl : NotesMapper {

    override fun toNotesViewState(notes: List<Note>, dateId: Int): NotesViewState {
        var checkedSize: Int = 0
        val notes = notes.map { note ->
            if (note.checked) {
                checkedSize++
            }

            NoteViewState(
                id = note.id,
                noteItemViewState = NoteItemViewState(
                    checked = note.checked,
                    text = note.text,
                ),
            )
        }

        return NotesViewState(
            notes = notes,
            progress = if (notes.isNotEmpty()) {
                checkedSize.toFloat() / notes.size.toFloat()
            } else {
                0f
            },
            dateId = dateId,
        )
    }
}
