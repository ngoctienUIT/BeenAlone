package com.tnt.beenalone.presentation.diary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.R
import com.tnt.beenalone.core.utils.NavDestinations
import com.tnt.beenalone.presentation.calendar.ItemDiary
import com.tnt.beenalone.ui.theme.BeenAloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(navController: NavController, viewModel: DiaryViewModel = hiltViewModel()) {
    val diaryUIState by viewModel.diaryUIState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Danh sách nhật ký", fontWeight = FontWeight(600)) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(30.dp)
                            .clickable { navController.popBackStack() },
                        painter = painterResource(id = R.drawable.close_icon),
                        contentDescription = "tnt",
                        tint = Color.Red
                    )
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            items(diaryUIState.listDiary.size) { index ->
                ItemDiary(diaryUIState.listDiary[index]) {
                    navController.navigate("${NavDestinations.ADD_DIARY_SCREEN}/${diaryUIState.listDiary[index].id}") {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiaryScreenPreview() {
    BeenAloneTheme {
        DiaryScreen(rememberNavController())
    }
}