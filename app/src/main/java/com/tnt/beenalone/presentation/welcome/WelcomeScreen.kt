package com.tnt.beenalone.presentation.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.core.utils.NavDestinations
import com.tnt.beenalone.models.User
import com.tnt.beenalone.presentation.welcome.page.InputBeenPage
import com.tnt.beenalone.presentation.welcome.page.InputInfoPage
import com.tnt.beenalone.presentation.welcome.page.WelcomePage
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavController, viewModel: WelcomeViewModel = hiltViewModel()) {
    val pagerState = rememberPagerState()
    var user by remember { mutableStateOf<User?>(null) }
    val scope = rememberCoroutineScope()

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                pageCount = 3,
                state = pagerState,
                userScrollEnabled = false
            ) { index ->
                when (index) {
                    0 -> WelcomePage {
                        scope.launch {
                            pagerState.animateScrollToPage(page = 1)
                        }
                    }

                    1 -> InputInfoPage { myUser ->
                        user = myUser
                        viewModel.saveProfile(user!!)
                        scope.launch {
                            pagerState.animateScrollToPage(page = 2)
                        }
                    }

                    2 -> InputBeenPage(
                        LocalDate.parse(user!!.birthday, viewModel.formatter)
                    ) { title, date ->
                        viewModel.saveSetting(date, title)
                        navController.navigate(NavDestinations.HOME_SCREEN) {
                            popUpTo(NavDestinations.WELCOME_SCREEN) { inclusive = true }
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxSize()) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    BeenAloneTheme {
        WelcomeScreen(rememberNavController())
    }
}