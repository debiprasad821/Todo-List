package com.example.todolist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.todolist.R
import com.example.todolist.ui.theme.White

@Composable
fun DeleteConfirmDialog(onClickYes: () -> Unit, onClickNo: () -> Unit) {
    Dialog(onDismissRequest = {}) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Are you sure you want to delete?",
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    Button(onClick = { onClickNo.invoke() }) {
                        Text(text = "NO")
                    }

                    Button(onClick = { onClickYes.invoke() }) {
                        Text(text = "YES")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun Prev() {
    DeleteConfirmDialog({}, {})
}