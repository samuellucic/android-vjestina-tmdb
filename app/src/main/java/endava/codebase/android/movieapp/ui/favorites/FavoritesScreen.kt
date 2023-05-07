package endava.codebase.android.movieapp.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import endava.codebase.android.movieapp.R
import endava.codebase.android.movieapp.ui.component.MovieCard
import endava.codebase.android.movieapp.ui.theme.Blue
import endava.codebase.android.movieapp.ui.theme.proximaNova
import endava.codebase.android.movieapp.ui.theme.spacing

// private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()
// private val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel,
    onNavigateToMovieDetails: (Int) -> Unit,
) {
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()

    FavoritesScreen(
        favoritesViewState = favoritesViewState,
        onFavoriteChange = viewModel::toggleFavorite,
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
    favoritesViewState: FavoritesViewState,
    onFavoriteChange: (Int) -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.favorites_screen_column_width)),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier
            .padding(
                vertical = MaterialTheme.spacing.medium
            ),
    ) {
        item(
            span = {
                GridItemSpan(Int.MAX_VALUE)
            }
        ) {
            Text(
                text = stringResource(id = R.string.favorites_title),
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
        }
        items(
            items = favoritesViewState.favoriteMovies,
            key = { favoriteMovie ->
                favoriteMovie.id
            },
        ) { favoriteMovie ->
            MovieCard(
                movieCardViewState = favoriteMovie.movieCardViewState,
                onFavoriteChange = { onFavoriteChange(favoriteMovie.id) },
                onClick = { onClick(favoriteMovie.id) },
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.movie_card_height))
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                    ),
            )
        }
    }
}

@Preview
@Composable
private fun FavoritesScreenPreview() {
//    val favoritesViewState by remember { mutableStateOf(favoritesViewState) }
//    MovieAppTheme {
//        FavoritesScreen(
//            favoritesViewState = favoritesViewState,
//            onFavoriteChange = {},
//            onClick = {},
//            modifier = Modifier,
//        )
//    }
}
