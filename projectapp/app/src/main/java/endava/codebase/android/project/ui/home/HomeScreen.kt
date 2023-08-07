package endava.codebase.android.project.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import endava.codebase.android.project.R
import endava.codebase.android.project.ui.component.DateItem

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigateToNotes: (Int) -> Unit,
    onNavigateToCalendar: () -> Unit,
) {
    val homeViewState: HomeViewState by viewModel.homeViewState.collectAsState()

    HomeScreen(
        homeViewState = homeViewState,
        onNavigateToNotes = onNavigateToNotes,
        onNavigateToCalendar = onNavigateToCalendar,
        onDelete = viewModel::removeDate,
    )
}

@Composable
fun HomeScreen(
    homeViewState: HomeViewState,
    onNavigateToNotes: (Int) -> Unit,
    onNavigateToCalendar: () -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier,
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
                items = homeViewState.dates,
                key = { date ->
                    date.id
                }
            ) { date ->
                DateItem(
                    dateItemViewState = date,
                    onClick = { onNavigateToNotes(date.id) },
                    onDelete = { onDelete(date.id) },
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.date_item_horizontal_pad),
                            vertical = dimensionResource(id = R.dimen.date_item_vertical_pad),
                        )
                )
            }
        }
        ExtendedFloatingActionButton(
            text = { Text(stringResource(id = R.string.new_date)) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                )
            },
            onClick = onNavigateToCalendar,
            modifier = Modifier
                .align(Alignment.End)
                .padding(dimensionResource(id = R.dimen.add_note_floating_padding))
        )
    }
}
