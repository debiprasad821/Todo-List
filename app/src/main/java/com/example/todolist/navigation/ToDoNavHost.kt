package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ToDoViewModel
import com.example.todolist.ui.screens.HomeScreen
import com.example.todolist.ui.screens.SplashScreen

@Composable
fun ToDoNavHost(toDoViewModel: ToDoViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Splash.route
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(toDoViewModel)
        }
    }
}