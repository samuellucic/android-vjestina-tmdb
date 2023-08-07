package endava.codebase.android.project.ui.noteedit

import androidx.compose.runtime.Composable
import endava.codebase.android.project.ui.component.NoteInput

@Composable
fun NoteEditRoute(
    viewModel: NoteEditViewModel,
    onBackClick: () -> Unit,
    changeOnClick: (() -> Unit) -> Unit,
) {
    changeOnClick {
        viewModel.saveNote(
            noteId = viewModel.id,
            text = viewModel.noteText,
        )
        onBackClick()
    }

    NoteEditScreen(
        noteText = viewModel.noteText,
        onNoteTextChange = viewModel::updateNoteText,
    )
}

@Composable
fun NoteEditScreen(
    noteText: String,
    onNoteTextChange: (String) -> Unit,
) {
    NoteInput(
        noteText = noteText,
        onNoteTextChange = onNoteTextChange,
    )
}
