package endava.codebase.android.movieapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import endava.codebase.android.movieapp.R
import endava.codebase.android.movieapp.mock.MoviesMock
import endava.codebase.android.movieapp.model.MovieCategory
import endava.codebase.android.movieapp.ui.component.MovieCard
import endava.codebase.android.movieapp.ui.component.MovieCardViewState
import endava.codebase.android.movieapp.ui.component.MovieCategoryLabel
import endava.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import endava.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import endava.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import endava.codebase.android.movieapp.ui.theme.Blue
import endava.codebase.android.movieapp.ui.theme.MovieAppTheme
import endava.codebase.android.movieapp.ui.theme.proximaNova
import endava.codebase.android.movieapp.ui.theme.spacing

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val trendingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(MovieCategory.POPULAR, MovieCategory.TOP_RATED),
    selectedMovieCategory = MovieCategory.POPULAR,
    movies = MoviesMock.getMoviesList(),
)
val newReleasesCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movieCategories = listOf(MovieCategory.NOW_PLAYING, MovieCategory.UPCOMING),
    selectedMovieCategory = MovieCategory.NOW_PLAYING,
    movies = MoviesMock.getMoviesList(),
)

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigateToMovieDetails: (Int) -> Unit,
) {
    val trendingCategoryViewState: HomeMovieCategoryViewState by viewModel.homeTrendingViewState.collectAsState()
    val newReleasesCategoryViewState: HomeMovieCategoryViewState by viewModel.homeNewReleasesViewState.collectAsState()

    MovieAppTheme {
        HomeScreen(
            trendingCategoryViewState = trendingCategoryViewState,
            newReleasesCategoryViewState = newReleasesCategoryViewState,
            onFavoriteChange = viewModel::toggleFavorite,
            onClick = onNavigateToMovieDetails,
            onCategoryClick = viewModel::changeCategory,
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.medium,
                ),
        )
    }
}

@Composable
fun HomeScreen(
    trendingCategoryViewState: HomeMovieCategoryViewState,
    newReleasesCategoryViewState: HomeMovieCategoryViewState,
    onFavoriteChange: (Int) -> Unit,
    onClick: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxHeight(),
    ) {
        item {
            MovieCategoryScreen(
                title = stringResource(id = R.string.trending_movies_title),
                homeMovieCategoryViewState = trendingCategoryViewState,
                onFavoriteChange = onFavoriteChange,
                onClick = onClick,
                onCategoryClick = onCategoryClick,
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small,
                    )
            )
        }
        item {
            MovieCategoryScreen(
                title = stringResource(id = R.string.new_releases_title),
                homeMovieCategoryViewState = newReleasesCategoryViewState,
                onFavoriteChange = onFavoriteChange,
                onClick = onClick,
                onCategoryClick = onCategoryClick,
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small,
                    )
            )
        }
    }
}

@Composable
private fun MovieCategoryScreen(
    title: String,
    homeMovieCategoryViewState: HomeMovieCategoryViewState,
    onFavoriteChange: (Int) -> Unit,
    onClick: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
    ) {
        Text(
            text = title,
            color = Blue,
            fontFamily = proximaNova,
            fontWeight = FontWeight.W800,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small,
                ),
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.mediumSmall),
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.mediumLarge,
                )
        ) {
            items(
                items = homeMovieCategoryViewState.movieCategories,
                key = { movieCategory ->
                    movieCategory.itemId
                }
            ) { movieCategory ->
                MovieCategoryLabel(
                    movieCategoryLabelViewState = MovieCategoryLabelViewState(
                        itemId = movieCategory.itemId,
                        isSelected = movieCategory.isSelected,
                        categoryText = movieCategory.categoryText,
                    ),
                    onCategoryClick = { onCategoryClick(movieCategory.itemId) },
                )
            }
        }
        LazyRow {
            items(
                items = homeMovieCategoryViewState.movies,
                key = { movie ->
                    movie.id
                },
            ) { movie ->
                MovieCard(
                    movieCardViewState = MovieCardViewState(
                        imageUrl = movie.movieCardViewState.imageUrl,
                        isFavorite = movie.movieCardViewState.isFavorite,
                    ),
                    onFavoriteChange = { onFavoriteChange(movie.id) },
                    onClick = { onClick(movie.id) },
                    modifier = Modifier
                        .width(160.dp)
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
fun HomeScreenPreview() {
    val mutableTrendingCategoryViewState by remember { mutableStateOf(trendingCategoryViewState) }
    val mutableNewReleasesCategoryViewState by remember { mutableStateOf(newReleasesCategoryViewState) }

    MovieAppTheme {
        HomeScreen(
            trendingCategoryViewState = mutableTrendingCategoryViewState,
            newReleasesCategoryViewState = mutableNewReleasesCategoryViewState,
            onFavoriteChange = {},
            onClick = {},
            onCategoryClick = {},
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                ),
        )
    }
}
