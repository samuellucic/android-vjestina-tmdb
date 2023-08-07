package endava.codebase.android.project.ui.noteinput

import androidx.compose.runtime.Composable
import endava.codebase.android.project.ui.component.NoteInput

@Composable
fun NoteInputRoute(
    viewModel: NoteInputViewModel,
    onBackClick: () -> Unit,
    changeOnClick: (() -> Unit) -> Unit,
) {
    changeOnClick {
        viewModel.saveNote(viewModel.noteText)
        onBackClick()
    }

    NoteInputScreen(
        noteText = viewModel.noteText,
        onNoteTextChange = viewModel::updateNoteText,
    )
}

@Composable
fun NoteInputScreen(
    noteText: String,
    onNoteTextChange: (String) -> Unit,
) {
    NoteInput(
        noteText = noteText,
        onNoteTextChange = onNoteTextChange,
    )
}
