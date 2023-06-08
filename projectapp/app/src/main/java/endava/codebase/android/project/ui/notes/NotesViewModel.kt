package endava.codebase.android.project.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.project.data.repository.note.NoteRepository
import endava.codebase.android.project.ui.notes.mapper.NotesMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteRepository,
    private val notesMapper: NotesMapper,
    private val dateId: Int,
) : ViewModel() {

    val notesViewState: StateFlow<NotesViewState> = noteRepository.notes(dateId)
        .map { notes ->
            notesMapper.toNotesViewState(notes, dateId)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = NotesViewState(listOf(), 0f, 0)
        )

    fun changeCheck(noteId: Int) {
        viewModelScope.launch {
            noteRepository.changeCheck(noteId)
        }
    }

    fun removeNote(noteId: Int) {
        viewModelScope.launch {
            noteRepository.removeNote(noteId)
        }
    }
}
