package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import endava.codebase.android.movieapp.ui.theme.spacing

data class MovieCardViewState(
    val imageUrl: String,
    val isFavorite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    onFavoriteChange: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .wrapContentSize(),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier,
        ) {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = "Movie title",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight(),
            )
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite,
                onFavoriteChange = onFavoriteChange,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(MaterialTheme.spacing.small),
            )
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val isFavorite = remember { mutableStateOf(false) }

    val movie = MoviesMock.getMoviesList()[0]
    MovieCard(
        movieCardViewState = MovieCardViewState(
            imageUrl = movie.imageUrl.toString(),
            isFavorite = isFavorite.value,
        ),
        onClick = { },
        onFavoriteChange = { isFavorite.value = !isFavorite.value },
        modifier = Modifier
            .height(450.dp)
            .width(300.dp)
            .padding(MaterialTheme.spacing.small),
    )
}
