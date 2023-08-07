package endava.codebase.android.project.ui.noteinput

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import endava.codebase.android.project.data.repository.note.NoteRepository
import kotlinx.coroutines.launch

class NoteInputViewModel(
    private val noteRepository: NoteRepository,
    private val dateId: Int,
) : ViewModel() {
    var noteText by mutableStateOf("")

    fun updateNoteText(input: String) {
        noteText = input
    }

    fun saveNote(
        text: String,
    ) {
        viewModelScope.launch {
            noteRepository.addNote(
                text = text.trim(),
                dateId = dateId,
            )
        }
    }
}
