package endava.codebase.android.movieapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import endava.codebase.android.movieapp.ui.theme.proximaNova

@Composable
fun UserScoreProgressBar(
    progress: Float,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    val progressAngle: Float = progress * 360
    val radius: Dp = size / 2
    val thickness: Dp = radius * 0.15f

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size),
    ) {
        Canvas(
            modifier = modifier.size(size),
        ) {
            drawCircle(
                color = Color.Green.copy(alpha = 0.3f),
                radius = radius.minus(thickness.div(2)).toPx(),
                style = Stroke(
                    width = thickness.toPx(),
                    cap = StrokeCap.Round
                )
            )
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = progressAngle,
                useCenter = false,
                style = Stroke(
                    width = thickness.toPx(),
                    cap = StrokeCap.Round
                ),
                size = Size(size.minus(thickness).toPx(), size.minus(thickness).toPx()),
                topLeft = Offset(thickness.div(2).toPx(), thickness.div(2).toPx())
            )
        }
        Text(
            text = String.format("%.1f", progress * 10),
            color = Color.Black,
            fontFamily = proximaNova,
            fontSize = 16.sp,
            lineHeight = 19.sp
        )
    }
}

@Preview
@Composable
fun UserScoreProgressBarPreview() {
    UserScoreProgressBar(
        progress = 0.71f,
        size = 82.dp,
        modifier = Modifier
    )
}
