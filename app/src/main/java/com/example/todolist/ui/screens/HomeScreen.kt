package com.example.todolist.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.ToDoViewModel
import com.example.todolist.db.ToDo
import com.example.todolist.events.UiEvent
import com.example.todolist.ui.components.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(toDoViewModel: ToDoViewModel) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var todoItem by remember { mutableStateOf(ToDo()) }  // Use by reference
    var isUpdateMode by remember { mutableStateOf(false) }

    val todoList by toDoViewModel.todoList.observeAsState(emptyList())

    LaunchedEffect(key1 = sheetState.currentValue) {
        if (sheetState.currentValue == ModalBottomSheetValue.Hidden) {
            isUpdateMode = false  // Reset update mode when the sheet is hidden
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetContent(
                todoTitle = if (isUpdateMode) todoItem.title else "",
                todoDescription = if (isUpdateMode) todoItem.description else "",
                buttonText = if (isUpdateMode) "Update" else "Add"
            ) { title, description ->
                if (isUpdateMode) {
                    toDoViewModel.onEvent(
                        UiEvent.OnUpdate(
                            todoItem.copy(
                                title = title,
                                description = description
                            )
                        )
                    )
                } else {
                    toDoViewModel.onEvent(UiEvent.OnAddToDo(title, description))
                }
                coroutineScope.launch {
                    sheetState.hide()
                    isUpdateMode = false  // Reset update mode after operation
                }
            }
        },
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ) {
        Scaffold(
            topBar = { TopBarComponent() },
            floatingActionButton = {
                FloatingActionButtonComponent {
                    coroutineScope.launch {
                        sheetState.show()
                        isUpdateMode = false  // Set mode to add when opening the bottom sheet
                        todoItem = ToDo()  // Clear todoItem
                    }
                }
            }
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                if (todoList.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                        modifier = Modifier.padding(top = 10.dp, start = 7.dp, end = 7.dp)
                    ) {
                        items(todoList) { item ->
                            ToDoRow(
                                item,
                                onClickComplete = { todo ->
                                    toDoViewModel.onEvent(UiEvent.OnCompleteClick(todo))
                                },
                                onClickDelete = { todo ->
                                    showDialog = true
                                    todoItem = todo
                                },
                                onClickUpdate = { todo ->
                                    isUpdateMode = true
                                    todoItem = todo
                                    coroutineScope.launch {
                                        sheetState.show()
                                    }
                                })
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_data_found),
                            contentDescription = ""
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = stringResource(id = R.string.no_data_found),
                            fontFamily = FontFamily(
                                Font(R.font.poppins_medium)
                            ),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        // Handle delete confirmation dialog
        if (showDialog) {
            DeleteConfirmDialog(onClickYes = {
                toDoViewModel.onEvent(UiEvent.OnDeleteClick(todoItem))
                showDialog = false
            }) {
                showDialog = false
            }
        }
    }
}
