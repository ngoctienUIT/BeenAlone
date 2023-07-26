package com.tnt.beenalone.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tnt.beenalone.R
import com.tnt.beenalone.core.utils.NavDestinations

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    LaunchedEffect(true) {
        navController.navigate(if (viewModel.isFist == true) NavDestinations.WELCOME_SCREEN else NavDestinations.HOME_SCREEN) {
            popUpTo(NavDestinations.WELCOME_SCREEN) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "tnt"
        )
    }
}