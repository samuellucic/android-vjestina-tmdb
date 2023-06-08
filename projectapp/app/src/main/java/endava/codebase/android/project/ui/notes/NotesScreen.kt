package endava.codebase.android.project.ui.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import endava.codebase.android.project.R
import endava.codebase.android.project.ui.component.NoteItem

@Composable
fun NotesRoute(
    viewModel: NotesViewModel,
    onNavigateToNoteEdit: (Int) -> Unit,
    onNavigateToNoteInput: (Int) -> Unit,
) {
    val notesViewState: NotesViewState by viewModel.notesViewState.collectAsState()

    NotesScreen(
        notesViewState = notesViewState,
        onNavigateToNoteInput = onNavigateToNoteInput,
        onCheck = viewModel::changeCheck,
        onClick = onNavigateToNoteEdit,
        onDelete = viewModel::removeNote,
        modifier = Modifier
    )
}

@Composable
fun NotesScreen(
    notesViewState: NotesViewState,
    onNavigateToNoteInput: (Int) -> Unit,
    onCheck: (Int) -> Unit,
    onClick: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
        ) {
            items(
                items = notesViewState.notes,
                key = { note ->
                    note.id
                }
            ) { note ->
                NoteItem(
                    noteItemViewState = note.noteItemViewState,
                    onCheck = { onCheck(note.id) },
                    onClick = { onClick(note.id) },
                    onDelete = { onDelete(note.id) },
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.note_item_horizontal_pad),
                            vertical = dimensionResource(id = R.dimen.note_item_vertical_pad),
                        )
                )
            }
        }
        ExtendedFloatingActionButton(
            text = { Text(stringResource(id = R.string.new_note)) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                )
            },
            onClick = { onNavigateToNoteInput(notesViewState.dateId) },
            modifier = Modifier
                .align(Alignment.End)
                .padding(dimensionResource(id = R.dimen.add_note_floating_padding))
        )
        LinearProgressIndicator(
            progress = notesViewState.progress,
            color = colorCalc(notesViewState.progress),
            strokeCap = StrokeCap.Butt,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.notes_score_size))
                .fillMaxWidth()
        )
    }
}

private fun colorCalc(progress: Float): Color {
    val redColor = Color.Red
    val yellowColor = Color.Yellow
    val greenColor = Color.Green
    val blueColor = Color.Blue
    val purpleColor = Color(0xA00F99)

    return if (progress < 0.25) {
        Color(
            green = (yellowColor.green - redColor.green) * progress + redColor.green,
            red = (yellowColor.red - redColor.red) * progress + redColor.red,
            blue = (yellowColor.blue - redColor.blue) * progress + redColor.blue,
        )
    } else if (progress < 0.5) {
        Color(
            green = (greenColor.green - yellowColor.green) * progress + yellowColor.green,
            red = (greenColor.red - yellowColor.red) * progress + yellowColor.red,
            blue = (greenColor.blue - yellowColor.blue) * progress + yellowColor.blue,
        )
    } else if (progress < 0.75) {
        Color(
            green = (blueColor.green - greenColor.green) * progress + greenColor.green,
            red = (blueColor.red - greenColor.red) * progress + greenColor.red,
            blue = (blueColor.blue - greenColor.blue) * progress + greenColor.blue,
        )
    } else {
        Color(
            green = (purpleColor.green - blueColor.green) * progress + blueColor.green,
            red = (purpleColor.red - blueColor.red) * progress + blueColor.red,
            blue = (purpleColor.blue - blueColor.blue) * progress + blueColor.blue,
        )
    }
}
