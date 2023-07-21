package com.tnt.beenalone.presentation.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.R
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import com.tnt.beenalone.utils.NavDestinations
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.color.colorChooser
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok") {

            }
            negativeButton("Cancel")
        }
    ) {
        colorChooser(
            colors = ColorPalette.Primary,
            subColors = ColorPalette.PrimarySub,
            initialSelection = 1
        ) {

        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Cài đặt",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF222B45),
                        textAlign = TextAlign.Center
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
        ) {
            ItemSetting(title = "Chỉnh sửa thông tin") {
                navController.navigate(NavDestinations.EDIT_PROFILE_SCREEN) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            ItemSetting(title = "Chỉnh sửa hiển thị") {
                navController.navigate(NavDestinations.EDIT_DISPLAY_SCREEN) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            ItemSetting(title = "Màu chủ đề") {
                dialogState.show()
            }
            ItemSetting(title = "Font chữ") {

            }
            ItemSetting(title = "Ghé thăm Fanpage") {

            }
            ItemSetting(title = "Thông tin ứng dụng") {

            }
        }
    }
}

@Composable
fun ItemSetting(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 15.dp)
        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.person_icon),
//                contentDescription = "",
//                tint = Color.Gray
//            )
//            Spacer(modifier = Modifier.width(5.dp))
            Text(text = title)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.arrow_forward_icon),
                contentDescription = "",
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    BeenAloneTheme {
        SettingScreen(rememberNavController())
    }
}