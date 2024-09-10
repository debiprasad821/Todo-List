package com.example.todolist.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.BackGroundColor
import com.example.todolist.ui.theme.White

@Composable
fun BottomSheetContent(
    todoTitle: String,
    todoDescription: String,
    buttonText: String,
    onClickAdd: (title: String, description: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(todoTitle, todoDescription) {
        title = todoTitle
        description = todoDescription
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = { Text(text = "Title") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = { Text(text = "Description") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            maxLines = 1
        )

        Button(
            onClick = {
                if (validate(title, description)) {
                    onClickAdd(title, description)
                    title = ""
                    description = ""
                } else {
                    Toast.makeText(
                        context,
                        "Title and description should not be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(top = 8.dp)
        ) {
            Text(text = buttonText, fontSize = 18.sp)
        }
    }
}


fun validate(title: String, description: String): Boolean {
    return title.isNotEmpty() && description.isNotEmpty()
}

