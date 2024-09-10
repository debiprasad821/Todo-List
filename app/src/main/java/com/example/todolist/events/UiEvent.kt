package com.example.todolist.events

import com.example.todolist.db.ToDo

sealed interface UiEvent {
    data class OnAddToDo(val title: String, val description: String) : UiEvent
    data class OnCompleteClick(val toDo: ToDo) : UiEvent
    data class OnDeleteClick(val toDo: ToDo) : UiEvent
    data class OnUpdate(val toDo: ToDo) : UiEvent
}