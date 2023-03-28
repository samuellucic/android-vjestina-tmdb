package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import endava.codebase.android.movieapp.R
import endava.codebase.android.movieapp.ui.theme.Blue
import endava.codebase.android.movieapp.ui.theme.spacing

@Composable
fun FavouriteButton(
    isFavourite: Boolean,
    onFavouriteChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        tint = Color.White,
        imageVector = if (isFavourite) {
            ImageVector.vectorResource(id = R.drawable.heart_full)
        } else {
            ImageVector.vectorResource(id = R.drawable.heart_blank)
        },
        contentDescription = "Favourite movie",
        modifier = modifier
            .clickable(
                onClick = onFavouriteChange,
            )
    )
}

@Preview
@Composable
fun FavouriteButtonPreview() {
    val isFavourite = remember { mutableStateOf(false) }

    FavouriteButton(
        isFavourite = isFavourite.value,
        onFavouriteChange = { isFavourite.value = !isFavourite.value },
        modifier = Modifier
            .alpha(0.4f)
            .background(
                color = Blue,
                shape = CircleShape
            )
            .padding(MaterialTheme.spacing.small),
    )
}
