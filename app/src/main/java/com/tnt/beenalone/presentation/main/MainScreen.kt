package com.tnt.beenalone.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tnt.beenalone.R
import com.tnt.beenalone.presentation.calendar.CalendarScreen
import com.tnt.beenalone.presentation.home.HomeScreen
import com.tnt.beenalone.presentation.rank.RankScreen
import com.tnt.beenalone.presentation.setting.SettingScreen
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import com.tnt.beenalone.ui.theme.Gold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.shadow(elevation = 3.dp),
                containerColor = Color.White,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page = 0)
                                    }
                                },
                            painter = painterResource(id = R.drawable.home_icon),
                            contentDescription = "home",
                            tint = if (pagerState.currentPage == 0) Gold else Color.LightGray
                        )
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page = 1)
                                    }
                                },
                            painter = painterResource(id = R.drawable.calendar_icon),
                            contentDescription = "calendar",
                            tint = if (pagerState.currentPage == 1) Gold else Color.LightGray
                        )
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page = 2)
                                    }
                                },
                            painter = painterResource(id = R.drawable.rank_icon),
                            contentDescription = "rank",
                            tint = if (pagerState.currentPage == 2) Gold else Color.LightGray
                        )
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    scope.launch {
                                        pagerState.animateScrollToPage(page = 3)
                                    }
                                },
                            painter = painterResource(id = R.drawable.settings_icon),
                            contentDescription = "setting",
                            tint = if (pagerState.currentPage == 3) Gold else Color.LightGray
                        )
                    }
                }
            )
        },
    ) {
        HorizontalPager(
            pageCount = 4,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
        ) { index ->
            when (index) {
                0 -> HomeScreen()
                1 -> CalendarScreen()
                2 -> RankScreen()
                3 -> SettingScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BeenAloneTheme {
        MainScreen()
    }
}