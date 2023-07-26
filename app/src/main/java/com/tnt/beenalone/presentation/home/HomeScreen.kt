package com.tnt.beenalone.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tnt.beenalone.R
import com.tnt.beenalone.core.extensions.toNumberStar
import com.tnt.beenalone.core.extensions.toRankIndex
import com.tnt.beenalone.core.rankList
import com.tnt.beenalone.ui.components.CustomCircularProgressbar
import com.tnt.beenalone.ui.components.CustomLinearProgressIndicator
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
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
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(top = paddingValue.calculateTopPadding())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomCircularProgressbar(viewModel.title, dateAlone = viewModel.days.toFloat())
            Spacer(modifier = Modifier.height(50.dp))
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(230.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.crown_icon),
                                contentDescription = "rank"
                            )
                            if (viewModel.imageUri == null) {
                                Image(
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(90.dp))
                                        .border(
                                            width = 5.dp,
                                            shape = RoundedCornerShape(90),
                                            color = rankList[viewModel.days.toRankIndex()]["color"] as Color
                                        ),
                                    painter = painterResource(id = R.drawable.avatar),
                                    contentDescription = "avatar",
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(90.dp))
                                        .border(
                                            width = 5.dp,
                                            shape = RoundedCornerShape(90),
                                            color = rankList[viewModel.days.toRankIndex()]["color"] as Color
                                        ),
                                    model = viewModel.imageUri,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = viewModel.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "Hạng cô đơn",
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Image(
                                modifier = Modifier.size(60.dp),
                                painter = painterResource(
                                    id = rankList[viewModel.days.toRankIndex()]["image"] as Int
                                ),
                                contentDescription = "rank"
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "${rankList[viewModel.days.toRankIndex()]["name"]}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = rankList[viewModel.days.toRankIndex()]["color"] as Color
                                )
                                if (viewModel.days.toNumberStar() > 0)
                                    Text(
                                        modifier = Modifier.padding(start = 3.dp),
                                        text = viewModel.days.toNumberStar().toString(),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = rankList[viewModel.days.toRankIndex()]["color"] as Color
                                    )
                                if (viewModel.days.toNumberStar() > 0)
                                    Image(
                                        painter = painterResource(id = R.drawable.star_icon),
                                        contentDescription = "star"
                                    )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomLinearProgressIndicator(dateAlone = viewModel.days.toFloat())
                }
            }
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