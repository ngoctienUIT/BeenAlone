package com.tnt.beenalone.presentation.edit_display

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.presentation.edit_profile.ItemEditText
import com.tnt.beenalone.ui.components.CustomAppBar
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.tnt.beenalone.ui.components.toast.CustomToastUtil.ToastSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDisplayScreen(
    navController: NavController,
    viewModel: EditDisplayViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    val dialogState = rememberMaterialDialogState()
    var datePicker by remember { mutableStateOf(LocalDate.now()) }
    var date by remember { mutableStateOf(LocalDate.now()) }
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val editDisplayUIState by viewModel.editDisplayUIState.collectAsState()

    LaunchedEffect(editDisplayUIState) {
        if (editDisplayUIState.date != null) {
            date = editDisplayUIState.date
            datePicker = editDisplayUIState.date
        }
        if (editDisplayUIState.title != null) {
            title = editDisplayUIState.title!!
        }
    }

    if (viewModel.showToastSuccess) {
        ToastSuccess("Lưu thành công")
        viewModel.showToastSuccess = false
    }

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
            allowedDateValidator = { it <= LocalDate.now() && it >= editDisplayUIState.startDate }
        ) { date = it }
    }

    Scaffold(
        topBar = {
            CustomAppBar(title = "Chỉnh sửa hiển thị", navController = navController) {
                viewModel.saveSetting(datePicker, title)
            }
        }
    ) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding() + 16.dp)) {
            ItemEditText("Tiêu đề", title, { text -> title = text })
            Spacer(modifier = Modifier.height(10.dp))
            ItemEditText("Ngày bắt đầu cô đơn", formatter.format(datePicker), {}) {
                dialogState.show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditDisplayScreenPreview() {
    BeenAloneTheme {
        EditDisplayScreen(rememberNavController())
    }
}