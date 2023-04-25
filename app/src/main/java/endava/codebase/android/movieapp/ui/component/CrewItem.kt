package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import endava.codebase.android.movieapp.mock.MoviesMock
import endava.codebase.android.movieapp.ui.theme.proximaNova

data class CrewItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = crewItemViewState.name,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.W800,
            fontFamily = proximaNova,
            lineHeight = 10.sp,
            modifier = Modifier,
        )
        Text(
            text = crewItemViewState.job,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = proximaNova,
            lineHeight = 10.sp,
            modifier = Modifier,
        )
    }
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
            .border(1.dp, Color.Black)
    )
}
