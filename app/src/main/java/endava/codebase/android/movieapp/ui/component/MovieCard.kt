package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import endava.codebase.android.movieapp.mock.MoviesMock
import endava.codebase.android.movieapp.ui.theme.Shapes
import endava.codebase.android.movieapp.ui.theme.spacing

data class MovieCardViewState(
    val title: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    onFavouriteChange: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .wrapContentSize(),
        shape = Shapes.medium,
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier,
        ) {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.title,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight(),
            )
            FavouriteButton(
                isFavourite = movieCardViewState.isFavorite,
                onFavouriteChange = onFavouriteChange,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val isFavourite = remember { mutableStateOf(false) }

    val movie = MoviesMock.getMoviesList()[0]
    MovieCard(
        movieCardViewState = MovieCardViewState(
            title = movie.title,
            imageUrl = movie.imageUrl.toString(),
            isFavorite = isFavourite.value,
        ),
        onClick = { },
        onFavouriteChange = { isFavourite.value = !isFavourite.value },
        modifier = Modifier
            .height(450.dp)
            .width(300.dp)
            .padding(MaterialTheme.spacing.small),
    )
}
