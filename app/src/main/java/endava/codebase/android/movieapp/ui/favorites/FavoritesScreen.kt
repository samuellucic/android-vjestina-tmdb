package endava.codebase.android.movieapp.ui.favorites

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import endava.codebase.android.movieapp.mock.MoviesMock
import endava.codebase.android.movieapp.ui.component.MovieCard
import endava.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import endava.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import endava.codebase.android.movieapp.ui.theme.Blue
import endava.codebase.android.movieapp.ui.theme.MovieAppTheme
import endava.codebase.android.movieapp.ui.theme.proximaNova
import endava.codebase.android.movieapp.ui.theme.spacing

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

private val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (Int) -> Unit,
) {
    val mutableFavoritesViewState = remember { mutableStateOf(favoritesViewState) }

    FavoritesScreen(
        favoritesViewState = mutableFavoritesViewState,
        onFavoriteChange = {},
        onClick = onNavigateToMovieDetails,
        modifier = Modifier
            .padding(
                start = MaterialTheme.spacing.small,
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.medium,
            )
    )
}

@Composable
fun FavoritesScreen(
    favoritesViewState: MutableState<FavoritesViewState>,
    onFavoriteChange: () -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier
            .padding(
                vertical = MaterialTheme.spacing.medium
            ),
    ) {
        Text(
            text = "Favorites",
            color = Blue,
            fontFamily = proximaNova,
            fontWeight = FontWeight.W800,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                )
        )
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
            columns = GridCells.Adaptive(minSize = 130.dp),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(
                items = favoritesViewState.value.favoriteMovies,
                key = { favoriteMovie ->
                    favoriteMovie.id
                },
            ) { favoriteMovie ->
                MovieCard(
                    movieCardViewState = favoriteMovie.movieCardViewState,
                    onFavoriteChange = onFavoriteChange,
                    onClick = { onClick(favoriteMovie.id) },
                    modifier = Modifier
                        .height(220.dp)
                        .padding(
                            horizontal = MaterialTheme.spacing.small,
                        ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun FavoritesScreenPreview() {
    val favoritesViewState = remember { mutableStateOf(favoritesViewState) }
    MovieAppTheme {
        FavoritesScreen(
            favoritesViewState = favoritesViewState,
            onFavoriteChange = {},
            onClick = {},
            modifier = Modifier,
        )
    }
}
