package endava.codebase.android.project.ui.noteedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.project.data.repository.note.NoteRepository
import kotlinx.coroutines.launch

class NoteEditViewModel(
    private val noteRepository: NoteRepository,
    val id: Int,
) : ViewModel() {

    var noteText by mutableStateOf("")

    init {
        viewModelScope.launch {
            noteText = noteRepository.note(id).text
        }
    }

    fun updateNoteText(input: String) {
        noteText = input
    }

    fun saveNote(noteId: Int, text: String) {
        viewModelScope.launch {
            noteRepository.changeText(
                noteId = noteId,
                text = text.trim(),
            )
        }
    }
}
