package com.tnt.beenalone.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tnt.beenalone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(title: String, navController: NavController, onSuccess: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222B45),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(30.dp)
                    .clickable { navController.popBackStack() },
                painter = painterResource(id = R.drawable.close_icon),
                contentDescription = "tnt"
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(30.dp)
                    .clickable { onSuccess() },
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = "tnt",
                tint = Color.Red
            )
        }
    )
}