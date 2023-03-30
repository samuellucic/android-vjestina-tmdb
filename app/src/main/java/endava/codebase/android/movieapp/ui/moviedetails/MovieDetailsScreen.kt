package endava.codebase.android.movieapp.ui.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import endava.codebase.android.movieapp.mock.MoviesMock
import endava.codebase.android.movieapp.ui.component.ActorCard
import endava.codebase.android.movieapp.ui.component.ActorCardViewState
import endava.codebase.android.movieapp.ui.component.CrewItem
import endava.codebase.android.movieapp.ui.component.CrewItemViewState
import endava.codebase.android.movieapp.ui.component.FavoriteButton
import endava.codebase.android.movieapp.ui.component.UserScoreProgressBar
import endava.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import endava.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import endava.codebase.android.movieapp.ui.theme.Blue
import endava.codebase.android.movieapp.ui.theme.Gray300
import endava.codebase.android.movieapp.ui.theme.MovieAppTheme
import endava.codebase.android.movieapp.ui.theme.proximaNova
import endava.codebase.android.movieapp.ui.theme.spacing

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(

) {
    val mutableMovieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }

    MovieDetailsScreen(
        movieDetailsViewState = mutableMovieDetailsViewState,
        modifier = Modifier,
    )
}

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        MovieScreen(
            movieDetailsViewState = movieDetailsViewState,
            onFavoriteChange = {},
            modifier = Modifier
                .height(300.dp),
        )
        Text(
            text = "Overview",
            color = Blue,
            fontFamily = proximaNova,
            fontWeight = FontWeight.W800,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.medium,
                )
        )
        Text(
            text = movieDetailsViewState.overview,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium
                )
        )
        CrewScreen(
            crew = movieDetailsViewState.crew,
            modifier = Modifier,
        )
        Text(
            text = "Top Billed Cast",
            color = Blue,
            fontFamily = proximaNova,
            fontWeight = FontWeight.W800,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.medium,
                )
        )
        CastScreen(
            cast = movieDetailsViewState.cast,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.medium
                )
                .height(259.dp)
        )
    }
}

@Composable
fun MovieScreen(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = "Movie",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Black,
                            1.0f to Color.Black,
                        ),
//                        start = Offset(x = 0.75f, y = 0.5f),
//                        end = Offset(x = 0.25f, y = 0.5f),
                    )
                )
                .fillMaxHeight(),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.medium
                ),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserScoreProgressBar(
                    progress = movieDetailsViewState.voteAverage,
                    size = 42.dp,
                    modifier = Modifier,
                )
                Text(
                    text = "User score",
                    color = Color.White,
                    fontFamily = proximaNova,
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp,
                    lineHeight = 17.sp
                )
            }
            Text(
                text = movieDetailsViewState.title,
                color = Gray300,
                fontFamily = proximaNova,
                fontWeight = FontWeight.W400,
                fontSize = 24.sp,
                lineHeight = 34.sp
            )
            FavoriteButton(
                isFavorite = movieDetailsViewState.isFavorite,
                onFavoriteChange = onFavoriteChange,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun CrewScreen(
    crew: List<CrewmanViewState>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        modifier = modifier,
    ) {
        items(
            items = crew,
            key = { crewman ->
                crewman.id
            },
        ) { crewman ->
            CrewItem(
                crewItemViewState = CrewItemViewState(
                    name = crewman.name,
                    job = crewman.job,
                ),
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.mediumSmall,
                    ),
            )
        }
    }
}

@Composable
fun CastScreen(
    cast: List<ActorViewState>,
    modifier: Modifier = Modifier,
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(count = 1),
        modifier = modifier,
    ) {
        items(
            items = cast,
            key = { actor ->
                actor.id
            },
        ) { actor ->
            ActorCard(
                actorCardViewState = ActorCardViewState(
                    imageUrl = actor.imageUrl,
                    name = actor.name,
                    character = actor.character,
                ),
                modifier = Modifier
                    .width(160.dp)
                    .padding(all = MaterialTheme.spacing.small),
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailPreview() {
    val movieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }

    MovieAppTheme(darkTheme = false) {
        MovieDetailsScreen(
            movieDetailsViewState = movieDetailsViewState,
            modifier = Modifier,
        )
    }
}
