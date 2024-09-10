package com.example.todolist.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.ui.theme.BackGroundColor
import com.example.todolist.ui.theme.White

@Composable
fun TopBarComponent() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.title),
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
            )
        },
        backgroundColor = BackGroundColor,
        contentColor = White,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = stringResource(id = R.string.menu),
                    Modifier.size(25.dp)
                )
            }
        }
    )
}

@Composable
@Preview
fun TopBarComponentPreview() {
    TopBarComponent()
}