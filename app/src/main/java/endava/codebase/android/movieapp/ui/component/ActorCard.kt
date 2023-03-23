package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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
import endava.codebase.android.movieapp.ui.theme.Shapes
import endava.codebase.android.movieapp.ui.theme.proximaNova


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
            .padding(6.dp)
            .wrapContentSize(),
        shape = Shapes.medium,
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
                    .weight(3.5f)
                    .fillMaxHeight(),
            )
            Text(
                text = actorCardViewState.name,
                modifier = Modifier
                    .padding(10.dp, 5.dp, 28.dp, 0.dp)
                    .wrapContentHeight()
                    .weight(1f),
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
                    .padding(10.dp, 0.dp, 7.dp, 5.dp)
                    .wrapContentHeight()
                    .weight(1f),
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
            .height(289.dp)
            .width(180.dp),
    )
}