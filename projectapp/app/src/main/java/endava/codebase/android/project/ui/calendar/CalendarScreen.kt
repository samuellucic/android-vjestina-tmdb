package endava.codebase.android.project.ui.calendar

import androidx.compose.runtime.Composable
import endava.codebase.android.project.ui.component.Calendar

@Composable
fun CalendarRoute(
    viewModel: CalendarViewModel,
    onBackClick: () -> Unit,
) {
    CalendarScreen(
        onClick = viewModel::addDate,
        onBackClick = onBackClick
    )
}

@Composable
fun CalendarScreen(
    onClick: (Long) -> Unit,
    onBackClick: () -> Unit,
) {
    Calendar(
        onClick = onClick,
        onBackClick = onBackClick,
    )
}
