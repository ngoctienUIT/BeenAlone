package com.tnt.beenalone.presentation.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tnt.beenalone.ui.theme.BeenAloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen() {
    Scaffold() {
        it
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    BeenAloneTheme {
        CalendarScreen()
    }
}