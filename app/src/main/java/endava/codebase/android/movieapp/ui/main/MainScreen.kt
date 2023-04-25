package endava.codebase.android.movieapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import endava.codebase.android.movieapp.R
import endava.codebase.android.movieapp.navigation.MOVIE_ID_KEY
import endava.codebase.android.movieapp.navigation.MovieDetailsDestination
import endava.codebase.android.movieapp.navigation.NavigationItem
import endava.codebase.android.movieapp.ui.favorites.FavoritesRoute
import endava.codebase.android.movieapp.ui.home.HomeRoute
import endava.codebase.android.movieapp.ui.moviedetails.MovieDetailsRoute
import endava.codebase.android.movieapp.ui.theme.Blue
import endava.codebase.android.movieapp.ui.theme.spacing

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route in listOf(
                NavigationItem.HomeDestination.route,
                NavigationItem.FavoritesDestination.route,
            )
        }
    }
    val showBackIcon = !showBottomBar

    val onNavigateToMovieDetails = { movieId: Int ->
        navController.navigate(MovieDetailsDestination.createNavigationRoute(movieId))
    }

    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) BackIcon(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.small)
                    )
                }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination,
                    ),
                    onNavigateToDestination = { navigationItem ->
                        navController.navigate(navigationItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    currentDestination = navBackStackEntry?.destination,
                )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeRoute(
                        onNavigateToMovieDetails = onNavigateToMovieDetails,
                    )
                }
                composable(NavigationItem.FavoritesDestination.route) {
                    FavoritesRoute(
                        onNavigateToMovieDetails = onNavigateToMovieDetails,
                    )
                }
                composable(
                    route = MovieDetailsDestination.route,
                    arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.StringType })
                ) {
                    MovieDetailsRoute()
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
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
        Icon(
            tint = Color(color = 0xFF9ACBA5),
            imageVector = ImageVector.vectorResource(id = R.drawable.tmdb_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center),
        )
    }
}

@Composable
private fun BackIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        tint = Color.White,
        imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
        contentDescription = null,
        modifier = modifier
            .clickable(
                onClick = onBackClick
            ),
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        destinations.forEach { destination ->
            val selected = destination.route == currentDestination?.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = if (selected) {
                            ImageVector.vectorResource(id = destination.selectedIconId)
                        } else {
                            ImageVector.vectorResource(id = destination.unselectedIconId)
                        },
                        contentDescription = null,
                    )
                },
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                label = {
                    Text(text = stringResource(id = destination.labelId))
                }
            )
        }
    }
}
