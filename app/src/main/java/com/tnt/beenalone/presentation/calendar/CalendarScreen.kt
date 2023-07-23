package com.tnt.beenalone.presentation.calendar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tnt.beenalone.R
import com.tnt.beenalone.core.listDayOfWeek
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen() {
    val now = LocalDate.now()
    val cal = Calendar.getInstance()
    val firstDate = LocalDate.of(now.year, now.month, 1)
    val lastDate = LocalDate.of(now.year, now.month, cal.getActualMaximum(Calendar.DATE))
    val calLastMonth = YearMonth
        .from(LocalDate.of(now.year, now.month.value - 1, 1))
        .atEndOfMonth().dayOfMonth

    Log.d("dayOfMonth", calLastMonth.toString())
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
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_icon),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Tháng ${now.month.value}")
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_forward_icon),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                List(listDayOfWeek.size) { index ->
                    Text(text = listDayOfWeek[index], fontWeight = FontWeight(600))
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                List(7) { index ->
                    Text(
                        text = if (index + 1 < firstDate.dayOfWeek.value) "${calLastMonth - firstDate.dayOfWeek.value + index + 2}" else "${2 + index - firstDate.dayOfWeek.value}",
                    )
                }
            }
            List(
                (lastDate.dayOfMonth - lastDate.dayOfWeek.value + firstDate.dayOfWeek.value - 8).div(
                    7
                )
            ) { indexWeek ->
                Log.d("index Week", indexWeek.toString())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    List(7) { index ->
                        Text(
                            text = "${indexWeek * 7 + index - firstDate.dayOfWeek.value + 9}",
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                List(7) { index ->
                    Text(
                        text = if (index < lastDate.dayOfWeek.value) "${lastDate.dayOfMonth - lastDate.dayOfWeek.value - index + 1}" else "${index - lastDate.dayOfWeek.value + 1}",
                    )
                }
            }
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