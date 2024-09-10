package com.example.todolist.repository

import androidx.lifecycle.LiveData
import com.example.todolist.db.ToDo

interface ToDoRepository {
    suspend fun insertToDo(toDo: ToDo) : Long
    fun getAllToDo(): LiveData<List<ToDo>>
    suspend fun updateToDo(toDo: ToDo): Int
    suspend fun deleteToDo(toDo: ToDo)
}