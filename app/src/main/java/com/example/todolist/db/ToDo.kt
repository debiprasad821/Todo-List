package com.example.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TODO_TAB")
data class ToDo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val isImportant: Boolean = false
)
