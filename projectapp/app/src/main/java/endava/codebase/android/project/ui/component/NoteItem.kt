package endava.codebase.android.project.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import endava.codebase.android.project.R

data class NoteItemViewState(
    val checked: Boolean,
    val text: String,
)

@Composable
fun NoteItem(
    noteItemViewState: NoteItemViewState,
    onCheck: () -> Unit,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        modifier = modifier
            .wrapContentSize(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                tint = Color.Unspecified,
                imageVector = if (noteItemViewState.checked) {
                    ImageVector.vectorResource(id = R.drawable.checkbox_ticked)
                } else {
                    ImageVector.vectorResource(id = R.drawable.checkbox)
                },
                contentDescription = null,
                modifier = modifier
                    .clickable(
                        onClick = onCheck
                    )
            )
            Text(
                text = noteItemViewState.text,
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable(
                        onClick = onClick,
                    )
                    .weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icons8_delete),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        onClick = onDelete
                    )
            )
        }
    }
}
