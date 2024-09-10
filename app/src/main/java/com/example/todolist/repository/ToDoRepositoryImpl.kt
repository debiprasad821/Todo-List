package com.example.todolist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todolist.db.ToDo
import com.example.todolist.db.ToDoDao
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val toDoDao: ToDoDao) : ToDoRepository {
    override suspend fun insertToDo(toDo: ToDo): Long {
        val id = toDoDao.insertToDo(toDo)
        Log.d("ToDoRepositoryImpl", "Inserted ToDo with ID: $id")
        return id
    }

    override fun getAllToDo(): LiveData<List<ToDo>> {
        return toDoDao.getAllToDo()
    }

    override suspend fun updateToDo(toDo: ToDo): Int {
        val rowsUpdated = toDoDao.updateToDo(toDo)
        Log.d("ToDoRepositoryImpl", "Rows updated: $rowsUpdated for ToDo ID: ${toDo.id}")
        return rowsUpdated
    }


    override suspend fun deleteToDo(toDo: ToDo) {
        toDoDao.deleteToDo(toDo)
    }
}