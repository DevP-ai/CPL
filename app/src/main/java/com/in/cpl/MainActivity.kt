package com.`in`.cpl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.`in`.cpl.foundation.theme.CPLTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.`in`.cpl.presentation.screens.AchievementScreen
import com.`in`.cpl.presentation.screens.HomeScreen
import com.`in`.cpl.presentation.screens.LoginScreen
import com.`in`.cpl.presentation.screens.ProfileInfoScreen
import com.`in`.cpl.presentation.screens.ProfileScreen
import com.`in`.cpl.presentation.screens.RankingScreen
import com.`in`.cpl.presentation.screens.SignUpScreen
import com.`in`.cpl.presentation.screens.SplashScreen
import com.`in`.cpl.util.BottomNavigation
import com.`in`.cpl.util.Destination


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPLTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: ""

                val currentRouteTrimmed by remember(currentRoute) {
                    derivedStateOf { currentRoute.substringBefore("?") }
                }
                val showBottomBar = currentRouteTrimmed != Destination.SplashScreen::class.qualifiedName
                        && currentRouteTrimmed != Destination.LoginScreen::class.qualifiedName
                        && currentRouteTrimmed != Destination.SignUpScreen::class.qualifiedName

                Scaffold(
                   bottomBar = {
                       if(showBottomBar){
                           BottomAppBar {
                               BottomNavigation.entries.forEach { bottomNavigation ->
                                   val selected = currentRouteTrimmed == bottomNavigation.route::class.qualifiedName
                                   NavigationBarItem(
                                       selected = selected,
                                       onClick = {
                                           navController.navigate(bottomNavigation.route){
                                               launchSingleTop = true
                                               restoreState = true
                                               popUpTo(navController.graph.startDestinationId){
                                                   saveState = true
                                               }
                                           }
                                       },
                                       icon = {
                                           Icon(
                                               imageVector = bottomNavigation.icon,
                                               contentDescription = ""
                                           )
                                       },
                                       label = {
                                           Text(
                                               text = bottomNavigation.label
                                           )
                                       }
                                   )
                               }
                           }
                       }
                   }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Destination.SplashScreen
                    ) {
                        // SplashScreen
                        composable<Destination.SplashScreen> {
                            SplashScreen {
                                val isLoggedIn = false
                                if(isLoggedIn){
                                    navController.navigate(Destination.NavigationGraph){
                                        popUpTo(Destination.SplashScreen){
                                            inclusive = true
                                        }
                                    }
                                }else{
                                    navController.navigate(Destination.LoginScreen){
                                        popUpTo(Destination.SplashScreen){
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }

                        // Login Screen
                        composable<Destination.LoginScreen> {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(Destination.NavigationGraph){
                                        popUpTo(Destination.LoginScreen){
                                            inclusive = true
                                        }
                                    }
                                },
                                onCreateAccountClick = {
                                    navController.navigate(Destination.SignUpScreen)
                                }
                            )
                        }

                        // SignUp Screen
                        composable <Destination.SignUpScreen>{
                            SignUpScreen(
                                onAccountCreated = {
                                    navController.navigate(Destination.NavigationGraph){
                                        popUpTo(Destination.SignUpScreen){
                                            inclusive = true
                                        }
                                    }
                                },
                                onCancel = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Navigation Graph with bottom nav

                        navigation <Destination.NavigationGraph>(
                            startDestination = Destination.HomeScreen
                        ){
                            // Home
                            composable <Destination.HomeScreen>{
                                HomeScreen(
                                    toProfileScreen = {
                                        navController.navigate(Destination.ProfileInfoScreen("101"))
                                    }
                                )
                            }
                            //Ranking
                            composable <Destination.RankingScreen>{
                                RankingScreen()
                            }
                            //Achievement
                            composable <Destination.AchievementScreen>{
                                AchievementScreen()
                            }
                            //Profile
                            composable <Destination.ProfileScreen>{
                                ProfileScreen()
                            }

                            // ProfileInfo Screen
                            composable <Destination.ProfileInfoScreen>{navBackStackEntry ->
                                val args = navBackStackEntry.toRoute<Destination.ProfileInfoScreen>()
                                ProfileInfoScreen(userId = args.userId, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

