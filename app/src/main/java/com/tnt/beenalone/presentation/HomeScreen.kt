package com.tnt.beenalone.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.beenalone.R
import com.tnt.beenalone.ui.theme.BeenAloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = "heart"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Been Alone",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF222B45),
                        textAlign = TextAlign.Center
                    )
                }
            },
        )
    }) { paddingValue ->
        Column(modifier = Modifier.padding(top = paddingValue.calculateTopPadding())) {
            Text(text = "Been Alone")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BeenAloneTheme {
        HomeScreen()
    }
}