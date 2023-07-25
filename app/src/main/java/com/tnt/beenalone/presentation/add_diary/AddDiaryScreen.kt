package com.tnt.beenalone.presentation.add_diary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.R
import com.tnt.beenalone.core.listFeeling
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDiaryScreen(
    navController: NavController,
    id: Long? = null,
    date: LocalDate? = null,
    viewModel: AddDiaryViewModel = hiltViewModel()
) {
    val addDiaryUIState by viewModel.addDiaryUIState.collectAsState()
    val density = LocalDensity.current
    val formatter = DateTimeFormatter.ofPattern("EEEE, dd 'thg' MM yyyy", Locale("vi", "VN"))

    if (id != null) {
        viewModel.getDiary(id)
    }

    if (viewModel.isSuccess) {
        viewModel.isSuccess = false
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Thêm nhật ký", fontWeight = FontWeight(600)) },
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
                },
                actions = {
                    if (id != null)
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .size(30.dp)
                                .clickable { viewModel.deleteDiary(id) },
                            painter = painterResource(id = R.drawable.trash_icon),
                            contentDescription = "",
                            tint = Color.Red
                        )
                }
            )
        },

        ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding() + 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Xin chào, ${addDiaryUIState.user?.name}!")
            Text(text = "Hôm nay bạn cảm thấy như thể nào?")
            Text(text = formatter.format(date ?: viewModel.date))
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    List(listFeeling.size) { index ->
                        Image(
                            modifier = Modifier
                                .size(50.dp)
                                .alpha(if (viewModel.selectedFeeling == index) 1f else 0.5f)
                                .clickable { viewModel.selectedFeeling = index },
                            painter = painterResource(id = listFeeling[index]),
                            contentDescription = ""
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = { viewModel.isVisibility = !viewModel.isVisibility }) {
                    Text(text = "Chi tiết")
                }
                Button(
                    onClick = {
                        if (date != null) {
                            viewModel.saveDiary(date)
                        } else {
                            viewModel.updateDiary(id!!)
                        }
                    },
                    enabled = viewModel.selectedFeeling != -1
                ) {
                    Text(text = if (id != null) "Cập nhật" else "Lưu")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(
                visible = viewModel.isVisibility,
                enter = slideInVertically { with(density) { -40.dp.roundToPx() } } + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(100.dp),
                    value = viewModel.content ?: "",
                    onValueChange = { text -> viewModel.content = text }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddDiaryScreenPreview() {
    BeenAloneTheme {
        AddDiaryScreen(rememberNavController(), null, LocalDate.now())
    }
}