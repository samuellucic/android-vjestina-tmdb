package endava.codebase.android.movieapp.ui.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import endava.codebase.android.movieapp.R
import endava.codebase.android.movieapp.ui.component.ActorCard
import endava.codebase.android.movieapp.ui.component.ActorCardViewState
import endava.codebase.android.movieapp.ui.component.CrewItem
import endava.codebase.android.movieapp.ui.component.CrewItemViewState
import endava.codebase.android.movieapp.ui.component.FavoriteButton
import endava.codebase.android.movieapp.ui.component.UserScoreProgressBar
import endava.codebase.android.movieapp.ui.theme.Blue
import endava.codebase.android.movieapp.ui.theme.Gray300
import endava.codebase.android.movieapp.ui.theme.proximaNova
import endava.codebase.android.movieapp.ui.theme.spacing

private const val OVERLAY_START_X = 90.75f
private const val OVERLAY_START_Y = 151.5f
private const val OVERLAY_END_X = 272.25f
private const val OVERLAY_END_Y = 151.5f

// private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()
// val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(
    viewModel: MovieDetailsViewModel
) {
    val movieDetailsViewState by viewModel.movieDetailsViewState.collectAsState()

    MovieDetailsScreen(
        movieDetailsViewState = movieDetailsViewState,
        onFavoriteChange = viewModel::toggleFavorite,
        modifier = Modifier,
    )
}

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier
            .verticalScroll(
                state = rememberScrollState(),
            )
    ) {
        MovieScreen(
            title = movieDetailsViewState.title,
            imageUrl = movieDetailsViewState.imageUrl,
            voteAverage = movieDetailsViewState.voteAverage,
            isFavorite = movieDetailsViewState.isFavorite,
            onFavoriteChange = onFavoriteChange,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.movie_screen_height)),
        )
        OverviewScreen(
            overview = movieDetailsViewState.overview,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium
                )
        )
        CrewScreen(
            crew = movieDetailsViewState.crew,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.crew_screen_height))
                .padding(
                    horizontal = MaterialTheme.spacing.medium
                )
        )
        CastScreen(
            cast = movieDetailsViewState.cast,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small
                )
                .height(dimensionResource(id = R.dimen.cast_screen_height))
        )
    }
}

@Composable
fun MovieScreen(
    title: String,
    imageUrl: String,
    voteAverage: Float,
    isFavorite: Boolean,
    onFavoriteChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.5f to Color.Black,
                            1.0f to Color.Black,
                        ),
                        start = Offset(
                            x = OVERLAY_START_X,
                            y = OVERLAY_START_Y,
                        ),
                        end = Offset(
                            x = OVERLAY_END_X,
                            y = OVERLAY_END_Y,
                        )
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
                    progress = voteAverage,
                    size = dimensionResource(id = R.dimen.user_score_size),
                    modifier = Modifier,
                )
                Text(
                    text = stringResource(id = R.string.user_score),
                    color = Color.White,
                    fontFamily = proximaNova,
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp,
                    lineHeight = 17.sp
                )
            }
            Text(
                text = title,
                color = Gray300,
                fontFamily = proximaNova,
                fontWeight = FontWeight.W400,
                fontSize = 24.sp,
                lineHeight = 34.sp
            )
            FavoriteButton(
                isFavorite = isFavorite,
                onFavoriteChange = onFavoriteChange,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun OverviewScreen(
    overview: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.overview_title),
            color = Blue,
            fontFamily = proximaNova,
            fontWeight = FontWeight.W800,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            modifier = Modifier
        )
        Text(
            text = overview,
            modifier = Modifier
        )
    }
}

@Composable
fun CrewScreen(
    crew: List<CrewmanViewState>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier
    ) {
        items(
            items = crew,
            key = { crewman ->
                crewman.id.toString() + crewman.job
            },
        ) { crewman ->
            CrewItem(
                crewItemViewState = CrewItemViewState(
                    name = crewman.name,
                    job = crewman.job,
                ),
                modifier = Modifier
            )
        }
    }
}

@Composable
fun CastScreen(
    cast: List<ActorViewState>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.top_billed_cast_title),
            color = Blue,
            fontFamily = proximaNova,
            fontWeight = FontWeight.W800,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small
                )
        )
        LazyRow(
            contentPadding = PaddingValues(all = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier,
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
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailPreview() {
//    val movieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }
//
//    MovieAppTheme(darkTheme = false) {
//        MovieDetailsScreen(
//            movieDetailsViewState = movieDetailsViewState,
//            onFavoriteChange = {},
//            modifier = Modifier,
//        )
//    }
}
