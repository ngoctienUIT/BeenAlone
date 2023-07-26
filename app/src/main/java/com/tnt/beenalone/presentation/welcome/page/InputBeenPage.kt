package com.tnt.beenalone.presentation.welcome.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.beenalone.presentation.edit_profile.ItemEditText
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun InputBeenPage(birthDate: LocalDate, onNext: (date: LocalDate) -> Unit) {
    val dialogState = rememberMaterialDialogState()
    var datePicker: LocalDate by remember { mutableStateOf(LocalDate.now()) }
    var date: LocalDate by remember { mutableStateOf(LocalDate.now()) }
    val startDate: LocalDate by remember { mutableStateOf(birthDate) }
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok") {
                datePicker = date
            }
            negativeButton("Cancel") {
                date = datePicker
            }
        }
    ) {
        datepicker(
            title = "Ngày bắt đầu cô đơn",
            initialDate = datePicker,
            allowedDateValidator = { it <= LocalDate.now() && it >= startDate }
        ) { date = it }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f))
        ItemEditText("Ngày bắt đầu cô đơn", formatter.format(datePicker), {}) {
            dialogState.show()
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            onClick = { onNext(datePicker) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(
                text = "Hoàn tất",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}