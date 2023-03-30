package endava.codebase.android.movieapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onNavigateToMovieDetails: (Int) -> Unit,
) {
    val mutableTrendingCategoryViewState = remember { mutableStateOf(trendingCategoryViewState) }
    val mutableNewReleasesCategoryViewState =
        remember { mutableStateOf(newReleasesCategoryViewState) }

    MovieAppTheme {
        HomeScreen(
            trendingCategoryViewState = mutableTrendingCategoryViewState,
            newReleasesCategoryViewState = mutableNewReleasesCategoryViewState,
            onFavoriteChange = {},
            onClick = onNavigateToMovieDetails,
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
    trendingCategoryViewState: MutableState<HomeMovieCategoryViewState>,
    newReleasesCategoryViewState: MutableState<HomeMovieCategoryViewState>,
    onFavoriteChange: () -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        MovieCategoryScreen(
            title = "Trending movies",
            homeMovieCategoryViewState = trendingCategoryViewState,
            onFavoriteChange = onFavoriteChange,
            onClick = onClick,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                )
                .weight(1f),
        )
        MovieCategoryScreen(
            title = "New releases",
            homeMovieCategoryViewState = newReleasesCategoryViewState,
            onFavoriteChange = onFavoriteChange,
            onClick = onClick,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                )
                .weight(1f),
        )
    }
}

@Composable
fun MovieCategoryScreen(
    title: String,
    homeMovieCategoryViewState: MutableState<HomeMovieCategoryViewState>,
    onFavoriteChange: () -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier,
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
                )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.mediumSmall),
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                )
        ) {
            homeMovieCategoryViewState.value.movieCategories.forEach { movieCategory ->
                MovieCategoryLabel(
                    movieCategoryLabelViewState = MovieCategoryLabelViewState(
                        itemId = movieCategory.itemId,
                        isSelected = movieCategory.isSelected,
                        categoryText = movieCategory.categoryText,
                    ),
                    onCategoryClick = {
                        when (movieCategory.itemId) {
                            0, 1 -> {
                                homeMovieCategoryViewState.value =
                                    homeScreenMapper.toHomeMovieCategoryViewState(
                                        movieCategories = listOf(
                                            MovieCategory.POPULAR,
                                            MovieCategory.TOP_RATED
                                        ),
                                        selectedMovieCategory = MovieCategory.values()[movieCategory.itemId],
                                        movies = MoviesMock.getMoviesList(),
                                    )
                            }
                            else -> {
                                homeMovieCategoryViewState.value =
                                    homeScreenMapper.toHomeMovieCategoryViewState(
                                        movieCategories = listOf(
                                            MovieCategory.NOW_PLAYING,
                                            MovieCategory.UPCOMING
                                        ),
                                        selectedMovieCategory = MovieCategory.values()[movieCategory.itemId],
                                        movies = MoviesMock.getMoviesList(),
                                    )
                            }
                        }
                    },
                )
            }
        }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(count = 1),
            modifier = Modifier
                .height(230.dp),
        ) {
            items(
                items = homeMovieCategoryViewState.value.movies,
                key = { movie ->
                    movie.id
                },
            ) { movie ->
                MovieCard(
                    movieCardViewState = MovieCardViewState(
                        imageUrl = movie.movieCardViewState.imageUrl,
                        isFavorite = movie.movieCardViewState.isFavorite,
                    ),
                    onFavoriteChange = onFavoriteChange,
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
    val mutableTrendingCategoryViewState = remember { mutableStateOf(trendingCategoryViewState) }
    val mutableNewReleasesCategoryViewState =
        remember { mutableStateOf(newReleasesCategoryViewState) }

    MovieAppTheme {
        HomeScreen(
            trendingCategoryViewState = mutableTrendingCategoryViewState,
            newReleasesCategoryViewState = mutableNewReleasesCategoryViewState,
            onFavoriteChange = {},
            onClick = {},
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.small,
                ),
        )
    }
}
