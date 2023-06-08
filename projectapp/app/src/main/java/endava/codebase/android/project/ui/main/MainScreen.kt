package endava.codebase.android.project.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import endava.codebase.android.project.R
import endava.codebase.android.project.navigation.DATE_ID_KEY
import endava.codebase.android.project.navigation.NOTE_ID_KEY
import endava.codebase.android.project.navigation.NavigationItem
import endava.codebase.android.project.navigation.NoteEditDestination
import endava.codebase.android.project.navigation.NoteInputDestination
import endava.codebase.android.project.navigation.NotesDestination
import endava.codebase.android.project.ui.calendar.CalendarRoute
import endava.codebase.android.project.ui.home.HomeRoute
import endava.codebase.android.project.ui.noteedit.NoteEditRoute
import endava.codebase.android.project.ui.noteinput.NoteInputRoute
import endava.codebase.android.project.ui.notes.NotesRoute
import endava.codebase.android.project.ui.theme.Blue
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBackIcon by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route in listOf(
                NotesDestination.route
            )
        }
    }
    val showActionIcon by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route in listOf(
                NoteInputDestination.route,
                NoteEditDestination.route,
            )
        }
    }

    var actionOnClick by remember {
        mutableStateOf({})
    }
    val changeOnClick = { onClick: () -> Unit ->
        actionOnClick = onClick
    }

    val onNavigateToNoteEdit = { noteId: Int ->
        navController.navigate(NoteEditDestination.createNavigationRoute(noteId))
    }
    val onNavigateToNoteInput = { dateId: Int ->
        navController.navigate(NoteInputDestination.createNavigationRoute(dateId))
    }
    val onNavigateToNotes = { dateId: Int ->
        navController.navigate(NotesDestination.createNavigationRoute(dateId))
    }
    val onNavigateToCalendar = {
        navController.navigate(NavigationItem.CalendarDestination.route)
    }
    val onBackClick: () -> Unit = {
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .size(size = dimensionResource(id = R.dimen.arrow_back_size))
                                .clickable(
                                    onClick = onBackClick
                                ),
                        )
                    }
                },
                actionIcon = {
                    if (showActionIcon) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier
                                .size(size = dimensionResource(id = R.dimen.arrow_back_size))
                                .clickable(
                                    onClick = actionOnClick
                                ),
                        )
                    }
                }
            )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding),
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeRoute(
                        viewModel = getViewModel(),
                        onNavigateToNotes = onNavigateToNotes,
                        onNavigateToCalendar = onNavigateToCalendar,
                    )
                }
                composable(NavigationItem.CalendarDestination.route) {
                    CalendarRoute(
                        viewModel = getViewModel(),
                        onBackClick = onBackClick,
                    )
                }
                composable(
                    route = NotesDestination.route,
                    arguments = listOf(navArgument(DATE_ID_KEY) { type = NavType.IntType })
                ) { navBackStackEntry ->
                    val dateId = navBackStackEntry.arguments?.getInt(DATE_ID_KEY)

                    NotesRoute(
                        viewModel = getViewModel {
                            parametersOf(dateId)
                        },
                        onNavigateToNoteEdit = onNavigateToNoteEdit,
                        onNavigateToNoteInput = onNavigateToNoteInput,
                    )
                }
                composable(
                    route = NoteInputDestination.route,
                    arguments = listOf(navArgument(DATE_ID_KEY) { type = NavType.IntType }),
                ) { navBackStackEntry ->
                    val dateId = navBackStackEntry.arguments?.getInt(DATE_ID_KEY)

                    NoteInputRoute(
                        viewModel = getViewModel {
                            parametersOf(dateId)
                        },
                        onBackClick = onBackClick,
                        changeOnClick = changeOnClick,
                    )
                }
                composable(
                    route = NoteEditDestination.route,
                    arguments = listOf(navArgument(NOTE_ID_KEY) { type = NavType.IntType })
                ) { navBackStackEntry ->
                    val noteId = navBackStackEntry.arguments?.getInt(NOTE_ID_KEY)

                    NoteEditRoute(
                        viewModel = getViewModel {
                            parametersOf(noteId)
                        },
                        onBackClick = onBackClick,
                        changeOnClick = changeOnClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .background(
                color = Blue
            )
            .height(dimensionResource(id = R.dimen.top_bar_height))
            .fillMaxWidth(),
    ) {
        navigationIcon?.invoke()
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
        actionIcon?.invoke()
    }
}
