package com.tnt.beenalone.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.beenalone.ui.theme.BeenAloneTheme

@Composable
fun CustomCircularProgressbar(
    title: String,
    size: Dp = 260.dp,
    foregroundIndicatorColor: Color = Color(0x99FE0000),
    shadowColor: Color = Color.LightGray,
    indicatorThickness: Dp = 15.dp,
    dateAlone: Float = 60f,
    animationDuration: Int = 1000,
) {
    var dateAloneRemember by remember { mutableFloatStateOf(0f) }

    val dateAloneAnimate = animateFloatAsState(
        targetValue = dateAloneRemember,
        animationSpec = tween(durationMillis = animationDuration), label = ""
    )

    LaunchedEffect(dateAlone) { dateAloneRemember = dateAlone % 100 }

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size)) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(shadowColor, Color.White),
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2),
                    radius = this.size.height / 2
                ),
                radius = this.size.height / 2,
                center = Offset(x = this.size.width / 2, y = this.size.height / 2)
            )

            drawCircle(
                color = Color.White,
                radius = (size / 2 - indicatorThickness).toPx(),
                center = Offset(x = this.size.width / 2, y = this.size.height / 2)
            )

            val sweepAngle = (dateAloneAnimate.value) * 360 / 100

            drawArc(
                color = foregroundIndicatorColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / 2).toPx(),
                    y = (indicatorThickness / 2).toPx()
                )
            )
        }

        // Display the data usage value
        DisplayText(title, dateAlone.toInt(), dateAloneAnimate)
    }
}

@Composable
private fun DisplayText(title: String, dateAlone: Int = 60, animateNumber: State<Float>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = (dateAlone.div(100) * 100 + animateNumber.value.toInt()).toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "Ngày",
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularProgressbarPreview() {
    BeenAloneTheme {
        CustomCircularProgressbar("Been Alone")
    }
}