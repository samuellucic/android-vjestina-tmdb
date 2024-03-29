package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
                contentDescription = null,
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
