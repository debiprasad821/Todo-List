package com.example.todolist.navigation

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem("splash")
    object Home : NavigationItem("home")
}
