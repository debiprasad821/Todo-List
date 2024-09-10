package com.example.todolist.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDo): Long

    @Query("SELECT * FROM TODO_TAB")
    fun getAllToDo(): LiveData<List<ToDo>>

    @Update
    suspend fun updateToDo(toDo: ToDo): Int

    @Delete
    suspend fun deleteToDo(toDo: ToDo)
}