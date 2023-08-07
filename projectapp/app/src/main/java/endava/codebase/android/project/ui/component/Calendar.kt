package endava.codebase.android.project.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(
    onClick: (Long) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onBackClick,
        confirmButton = {
            Button(
                onClick = {
                    onClick(datePickerState.selectedDateMillis!!)
                    onBackClick()
                },
                enabled = datePickerState.selectedDateMillis != null,
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = onBackClick,
            ) {
                Text("Cancel")
            }
        },
        modifier = modifier
            .fillMaxSize(),
    ) {
        DatePicker(
            state = datePickerState,
        )
    }
}
