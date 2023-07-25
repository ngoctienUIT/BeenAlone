package com.tnt.beenalone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.beenalone.R
import com.tnt.beenalone.core.extensions.lastDate
import com.tnt.beenalone.core.extensions.nextMonth
import com.tnt.beenalone.core.extensions.previousMonth
import com.tnt.beenalone.core.listDayOfWeek
import com.tnt.beenalone.core.listFeeling
import com.tnt.beenalone.data.local.entity.DiaryEntity
import java.time.LocalDate

@Composable
fun CustomCalendarTable(
    date: LocalDate,
    localDate: LocalDate = LocalDate.now(),
    listDiary: List<DiaryEntity> = listOf(),
    onChangeMonth: ((month: LocalDate) -> Unit)? = null,
    onChangeDate: ((date: LocalDate) -> Unit)? = null,
) {
    var now by remember { mutableStateOf(localDate) }
    val firstDate = LocalDate.of(now.year, now.month, 1)
    val lastDate = now.lastDate()
    val calLastMonth = now.previousMonth().lastDate().dayOfMonth
    val weekNumber =
        (lastDate.dayOfMonth - lastDate.dayOfWeek.value + firstDate.dayOfWeek.value - 8).div(7)

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp - 36.dp

    Card(
        modifier = Modifier.padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 15.dp, horizontal = 8.dp)) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        now = now.previousMonth()
                        onChangeMonth?.let { it(now) }
                    },
                    painter = painterResource(id = R.drawable.arrow_back_icon),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Tháng ${now.monthValue} năm ${now.year}",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.clickable {
                        now = now.nextMonth()
                        onChangeMonth?.let { it(now) }
                    },
                    painter = painterResource(id = R.drawable.arrow_forward_icon),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                List(listDayOfWeek.size) { index ->
                    Text(
                        text = listDayOfWeek[index],
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp
                    )
                }
            }
            List(weekNumber + 2) { indexWeek ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    List(7) { index ->
                        var isEnable = true
                        val content = when (indexWeek) {
                            0 -> {
                                if (index + 1 < firstDate.dayOfWeek.value) {
                                    isEnable = false
                                    "${calLastMonth - firstDate.dayOfWeek.value + index + 2}"
                                } else "${2 + index - firstDate.dayOfWeek.value}"
                            }

                            weekNumber + 1 -> {
                                if (index < lastDate.dayOfWeek.value) "${lastDate.dayOfMonth - lastDate.dayOfWeek.value + index + 1}"
                                else {
                                    isEnable = false
                                    "${index - lastDate.dayOfWeek.value + 1}"
                                }
                            }

                            else -> {
                                "${(indexWeek - 1) * 7 + index - firstDate.dayOfWeek.value + 9}"
                            }
                        }
                        val list =
                            if (isEnable) listDiary.filter { it.day == content.toInt() && it.month == now.monthValue && it.year == now.year }
                                .sortedBy { it.time } else listOf()
                        Row(
                            modifier = Modifier.width(screenWidth / 7),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.clickable {
                                    if (isEnable) onChangeDate?.let {
                                        it(LocalDate.of(now.year, now.month, content.toInt()))
                                    } else {
                                        now = if (content.toInt() > 15) now.previousMonth()
                                        else now.nextMonth()
                                    }
                                },
                                text = content,
                                color = if (!isEnable) Color.Gray else Color.Unspecified,
                                fontSize = 14.sp,
                                fontWeight = if (content.toInt() == date.dayOfMonth && date.monthValue == now.monthValue && date.year == now.year) FontWeight.Bold else null
                            )
                            if (list.isNotEmpty()) Spacer(modifier = Modifier.width(3.dp))
                            if (list.isNotEmpty())
                                Image(
                                    modifier = Modifier.size(25.dp),
                                    painter = painterResource(id = listFeeling[list[0].feel]),
                                    contentDescription = ""
                                )
                        }
                    }
                }
            }
        }
    }
}