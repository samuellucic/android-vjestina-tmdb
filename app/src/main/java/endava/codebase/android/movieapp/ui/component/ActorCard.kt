package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import endava.codebase.android.movieapp.mock.MoviesMock
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
        modifier = modifier
            .wrapContentSize(),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = actorCardViewState.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            )
            Text(
                text = actorCardViewState.name,
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.large,
                        MaterialTheme.spacing.small
                    )
                    .wrapContentHeight(),
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W800,
                fontFamily = proximaNova,
                fontSize = 14.sp,
                lineHeight = 15.sp,
            )
            Text(
                text = actorCardViewState.character,
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.default,
                        MaterialTheme.spacing.small,
                        MaterialTheme.spacing.medium
                    )
                    .wrapContentHeight(),
                color = Gray700,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W400,
                fontFamily = proximaNova,
                lineHeight = 14.sp,
            )
        }
    }

}

@Preview
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor()

    ActorCard(
        ActorCardViewState(
            actor.imageUrl.toString(),
            actor.name,
            actor.character
        ),
        Modifier
            .height(320.dp)
            .width(150.dp)
            .padding(6.dp),
    )
}
