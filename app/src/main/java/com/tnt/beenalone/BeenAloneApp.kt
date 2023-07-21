package com.tnt.beenalone

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.presentation.edit_display.EditDisplayScreen
import com.tnt.beenalone.presentation.edit_profile.EditProfileScreen
import com.tnt.beenalone.presentation.main.MainScreen
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import com.tnt.beenalone.utils.NavDestinations

@Composable
fun BeenAloneApp() {
    BeenAloneTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = NavDestinations.HOME_SCREEN) {
                composable(NavDestinations.HOME_SCREEN) {
                    MainScreen(navController)
                }
                composable(NavDestinations.EDIT_PROFILE_SCREEN) {
                    EditProfileScreen(navController)
                }
                composable(NavDestinations.EDIT_DISPLAY_SCREEN) {
                    EditDisplayScreen(navController)
                }
            }
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