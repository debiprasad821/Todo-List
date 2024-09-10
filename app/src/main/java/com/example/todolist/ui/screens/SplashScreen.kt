package com.example.todolist.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.navigation.NavigationItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        var isScaled by remember { mutableStateOf(false) }
        val scale by animateFloatAsState(
            targetValue = if (isScaled) 1f else 0f,
            animationSpec = tween(durationMillis = 3000)
        )

        LaunchedEffect(key1 = Unit) {
            isScaled = true
            delay(4000)
            navController.navigate(NavigationItem.Home.route) {
                popUpTo(route = NavigationItem.Splash.route) {
                    inclusive = true
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_todo_list),
            contentDescription = stringResource(
                id = R.string.todo_img
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .size(170.dp)
                .scale(scale)
        )

        Text(
            text = stringResource(id = R.string.welcome_message),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
//    SplashScreen()
}