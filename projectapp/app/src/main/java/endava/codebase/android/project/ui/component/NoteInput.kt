package endava.codebase.android.project.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteInput(
    noteText: String,
    onNoteTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize(),
    ) {
        TextField(
            value = noteText,
            onValueChange = onNoteTextChange,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
