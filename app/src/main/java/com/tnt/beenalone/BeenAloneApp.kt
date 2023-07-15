package com.tnt.beenalone

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.presentation.HomeScreen
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import com.tnt.beenalone.utils.NavDestinations

@Composable
fun BeenAloneApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavDestinations.HOME_SCREEN) {
        composable(NavDestinations.HOME_SCREEN) {
            HomeScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BeenAloneAppPreview() {
    BeenAloneTheme {
        BeenAloneApp()
    }
}