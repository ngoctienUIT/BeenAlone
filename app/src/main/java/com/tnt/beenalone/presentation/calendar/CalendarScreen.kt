package com.tnt.beenalone.presentation.calendar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.core.listFeeling
import com.tnt.beenalone.core.utils.NavDestinations
import com.tnt.beenalone.ui.components.CustomCalendarTable
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavController, viewModel: CalendarViewModel = hiltViewModel()) {
    val formatter = DateTimeFormatter.ofPattern("EEEE, dd 'thg' MM yyyy", Locale("vi", "VN"))
    val diaryDateUIState by viewModel.diaryDateUIState.collectAsState()
    val diaryMonthUIState by viewModel.diaryMonthUIState.collectAsState()
    val density = LocalDensity.current

    LaunchedEffect(viewModel.date) {
        viewModel.getDiaryByDate(viewModel.date)
    }

    LaunchedEffect(viewModel.month) {
        viewModel.getDiaryByMonth(viewModel.month)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Nhật ký", fontWeight = FontWeight(600))
                },
                actions = {

                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("${NavDestinations.ADD_DIARY_SCREEN}/${viewModel.date.dayOfMonth}/${viewModel.date.monthValue}/${viewModel.date.year}") {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            CustomCalendarTable(
                viewModel.date,
                onChangeMonth = { changeMonth -> viewModel.month = changeMonth },
                listDiary = diaryMonthUIState.listDiary
            ) { d -> viewModel.date = d }
            Text(
                modifier = Modifier.padding(16.dp, 10.dp),
                text = formatter.format(viewModel.date),
                fontWeight = FontWeight(600)
            )
            LazyColumn {
                item {
                    AnimatedVisibility(
                        visible = diaryDateUIState.listDiary.isEmpty(),
                        enter = slideInVertically { with(density) { -40.dp.roundToPx() } } + expandVertically(
                            expandFrom = Alignment.Top
                        ) + fadeIn(initialAlpha = 0.3f),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Không có nhật ký",
                            fontWeight = FontWeight(600),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                items(diaryDateUIState.listDiary.size) { index ->
                    Card(
                        modifier = Modifier.padding(16.dp, 10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .fillMaxHeight(),
                            ) {
                                Text(text = diaryDateUIState.listDiary[index].content ?: "__")
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "${diaryDateUIState.listDiary[index].time} ${diaryDateUIState.listDiary[index].day}/${diaryDateUIState.listDiary[index].month}/${diaryDateUIState.listDiary[index].year}",
                                    fontWeight = FontWeight(600),
                                    fontSize = 13.sp
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                modifier = Modifier.size(50.dp),
                                painter = painterResource(id = listFeeling[diaryDateUIState.listDiary[index].feel]),
                                contentDescription = ""
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    BeenAloneTheme {
        CalendarScreen(rememberNavController())
    }
}