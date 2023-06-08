package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import endava.codebase.android.movieapp.ui.theme.Gray700
import endava.codebase.android.movieapp.ui.theme.proximaNova
import endava.codebase.android.movieapp.ui.theme.spacing

data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = modifier
            .wrapContentSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier,
        ) {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = actorCardViewState.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f),
            )
            Text(
                text = actorCardViewState.name,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W800,
                fontFamily = proximaNova,
                fontSize = 14.sp,
                lineHeight = 15.sp,
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.extraSmall
                    )
            )
            Text(
                text = actorCardViewState.character,
                color = Gray700,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W400,
                fontFamily = proximaNova,
                lineHeight = 14.sp,
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.extraSmall,
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.small
                    )
            )
        }
    }
}
