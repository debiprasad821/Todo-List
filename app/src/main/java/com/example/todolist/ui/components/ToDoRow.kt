package com.example.todolist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.db.ToDo

@Composable
fun ToDoRow(
    item: ToDo,
    onClickComplete: (todo: ToDo) -> Unit,
    onClickDelete: (todo: ToDo) -> Unit,
    onClickUpdate: (todo: ToDo) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 3.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                onClickComplete.invoke(
                    item.copy(isCompleted = !item.isCompleted)
                )
            }) {
                if (item.isCompleted) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "",
                        Modifier
                            .size(22.dp)
                            .weight(.2f)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.outlined_circle),
                        contentDescription = "",
                        Modifier
                            .size(22.dp)
                            .weight(.2f)
                    )
                }
            }

            Column(Modifier.weight(.7f)) {
                Text(
                    text = item.title,
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    fontSize = 18.sp,
                    style = if (item.isCompleted) TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.LightGray
                    ) else TextStyle(
                        textDecoration = TextDecoration.None
                    )
                )
                Text(
                    text = item.description,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 16.sp,
                    style = if (item.isCompleted) TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.LightGray
                    ) else TextStyle(
                        textDecoration = TextDecoration.None
                    )
                )
            }

            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    Modifier
                        .size(22.dp)
                        .weight(.1f)
                )

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(onClick = {
                        onClickUpdate.invoke(item)
                        expanded = false
                    }) {
                        Text(text = "Update")
                    }

                    DropdownMenuItem(onClick = {
                        onClickDelete.invoke(item)
                        expanded = false
                    }) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ToDoRowPreview() {
    ToDoRow(ToDo(), { todo -> }, { todo -> }, { todo -> })
}