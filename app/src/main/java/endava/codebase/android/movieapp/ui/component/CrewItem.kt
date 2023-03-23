package endava.codebase.android.movieapp.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import endava.codebase.android.movieapp.mock.MoviesMock
import endava.codebase.android.movieapp.ui.theme.proximaNova

data class CrewItemViewState(
    val name: String,
    val profession: String,
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
    modifier: Modifier = Modifier,
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.W800)) {
                append(crewItemViewState.name)
            }
            append("\n${crewItemViewState.profession}")
        },
        color = Color.Black,
        modifier = modifier,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        fontFamily = proximaNova,
        lineHeight = 20.sp,
    )
}

@Preview
@Composable
private fun CrewItemPreview() {
    val crewman = MoviesMock.getCrewman()

    CrewItem(
        CrewItemViewState(
            crewman.name,
            crewman.job,
        ),
        modifier = Modifier
    )
}
