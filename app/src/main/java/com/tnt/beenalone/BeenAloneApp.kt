package com.tnt.beenalone

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tnt.beenalone.presentation.edit_display.EditDisplayScreen
import com.tnt.beenalone.presentation.edit_profile.EditProfileScreen
import com.tnt.beenalone.presentation.main.MainScreen
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import com.tnt.beenalone.core.utils.NavDestinations
import com.tnt.beenalone.presentation.add_diary.AddDiaryScreen
import java.time.LocalDate

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
                composable(
                    "${NavDestinations.ADD_DIARY_SCREEN}/{day}/{month}/{year}",
                    arguments = listOf(
                        navArgument("day") { type = NavType.IntType },
                        navArgument("month") { type = NavType.IntType },
                        navArgument("year") { type = NavType.IntType },
                    )
                ) { backStackEntry ->
                    val day = backStackEntry.arguments?.getInt("day")
                    val month = backStackEntry.arguments?.getInt("month")
                    val year = backStackEntry.arguments?.getInt("year")
                    if (day != null && month != null && year != null)
                        AddDiaryScreen(navController, LocalDate.of(year, month, day))
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