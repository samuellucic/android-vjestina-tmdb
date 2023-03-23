package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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

data class MovieCardViewState(
    val title: String,
    val imageUrl: String,
    val isFavorite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
) {
    val isFavourite = remember { mutableStateOf(movieCardViewState.isFavorite) }
    Card(
        modifier = modifier
            .padding(6.dp)
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
                modifier = Modifier
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
            FavouriteButton(
                isFavourite = isFavourite.value,
                onFavouriteChange = { isFavourite.value = it },
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[0]
    MovieCard(
        movieCardViewState = MovieCardViewState(
            title = movie.title,
            imageUrl = movie.imageUrl.toString(),
            isFavorite = movie.isFavorite,
        ),
        modifier = Modifier
            .height(450.dp)
            .width(300.dp)
    )
}