package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import endava.codebase.android.movieapp.ui.theme.Gray700
import endava.codebase.android.movieapp.ui.theme.spacing

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    onCategoryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .clickable(
                onClick = onCategoryClick,
            )
    ) {
        Text(
            text = movieCategoryLabelViewState.categoryText.getCategoryText(),
            lineHeight = 22.sp,
            fontSize = 16.sp,
            style = if (movieCategoryLabelViewState.isSelected) {
                TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.W700
                )
            } else {
                TextStyle(
                    color = Gray700,
                    fontWeight = FontWeight.W400
                )
            },
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.extraSmall)
        )
        if (movieCategoryLabelViewState.isSelected) {
            Divider(
                color = Color.Black,
                thickness = 3.dp,
            )
        }
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreviewSelected() {
    MovieCategoryLabel(
        MovieCategoryLabelViewState(
            1,
            true,
            MovieCategoryLabelTextStringViewState("Movies")
        ),
        onCategoryClick = {},
    )
}

@Preview
@Composable
fun MovieCategoryLabelPreviewDeselected() {
    MovieCategoryLabel(
        MovieCategoryLabelViewState(
            1,
            false,
            MovieCategoryLabelTextStringViewState("Movies")
        ),
        onCategoryClick = {},
    )
}
