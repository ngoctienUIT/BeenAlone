package com.tnt.beenalone.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.tnt.beenalone.ui.theme.Gold

@Composable
fun CustomLinearProgressIndicator(
    foregroundIndicatorColor: Color = Color(0x99FE0000),
    dateAlone: Float = 60f,
    animationDuration: Int = 5000,
) {
    var dateAloneRemember by remember { mutableStateOf(0f) }

    val dateAloneAnimate by animateFloatAsState(
        targetValue = dateAloneRemember,
        animationSpec = tween(durationMillis = animationDuration)
    )

    var boxSize by remember { mutableStateOf(Size.Zero) }

    LaunchedEffect(dateAlone) { dateAloneRemember = dateAlone % 100 }
    val density = LocalDensity.current
    val divAlone = dateAlone.toInt().div(100)

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = (divAlone * 100).toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Text(
                text = ((divAlone + 1) * 100).toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(16.dp)
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(90),
                    color = Gold
                )
                .onGloballyPositioned { layoutCoordinates ->
                    boxSize = layoutCoordinates.size.toSize()
                },
            contentAlignment = Alignment.CenterStart
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .width((with(density) { (((dateAlone % 100) / 100f) * boxSize.width).toDp() }))
                    .height(10.dp)
                    .clip(RoundedCornerShape(20.dp)), // Rounded edges
                progress = dateAloneAnimate,
                color = foregroundIndicatorColor,
                trackColor = Color.Transparent
            )
        }
    }
}