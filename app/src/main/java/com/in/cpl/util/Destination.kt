package com.`in`.cpl.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    data object SplashScreen: Destination()

    @Serializable
    data object LoginScreen: Destination()

    @Serializable
    data object SignUpScreen: Destination()

    @Serializable
    data object NavigationGraph: Destination()

    @Serializable
    data object HomeScreen: Destination()

    @Serializable
    data object RankingScreen: Destination()

    @Serializable
    data object AchievementScreen: Destination()

    @Serializable
    data object ProfileScreen: Destination()

    @Serializable
    data class ProfileInfoScreen(val userId: String): Destination()
}

enum class BottomNavigation(val label: String, val icon: ImageVector, val route: Destination){
    HOME(label = "Home", icon = Icons.Filled.Home, route = Destination.HomeScreen),
    RANKING(label = "Ranking", icon = Icons.Filled.KeyboardArrowUp, route = Destination.RankingScreen),
    ACHIEVEMENT(label = "Achievement", icon = Icons.Filled.Star, route = Destination.AchievementScreen),
    PROFILE(label = "Profile", icon = Icons.Filled.AccountCircle, route = Destination.ProfileScreen)
}