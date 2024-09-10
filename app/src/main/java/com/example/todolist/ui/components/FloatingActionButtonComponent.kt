package com.example.todolist.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.ui.theme.BackGroundColor
import com.example.todolist.ui.theme.White

@Composable
fun FloatingActionButtonComponent(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = {
            onClick.invoke()
        },
        backgroundColor = BackGroundColor,
        contentColor = White,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add),
            Modifier.size(25.dp)
        )
    }
}