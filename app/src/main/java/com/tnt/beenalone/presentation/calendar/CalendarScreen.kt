package com.tnt.beenalone.presentation.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tnt.beenalone.ui.components.CustomCalendarTable
import com.tnt.beenalone.ui.theme.BeenAloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Nhật ký")
                },
                actions = {

                }
            )
        },
    ) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding() + 16.dp)) {
            CustomCalendarTable()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    BeenAloneTheme {
        CalendarScreen()
    }
}