package com.example.todolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.db.ToDo
import com.example.todolist.events.UiEvent
import com.example.todolist.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {

    private fun insertToDo(toDo: ToDo) {
        viewModelScope.launch {
            val id = toDoRepository.insertToDo(toDo)
            toDo.id = id.toInt()  // Ensure the ToDo object has the correct ID after insertion
            Log.d("ToDoViewModel", "Inserted ToDo with ID: $id")
        }
    }

    private fun updateToDo(toDo: ToDo) {
        viewModelScope.launch {
            val rowsUpdated = toDoRepository.updateToDo(toDo)
            Log.d("ToDoViewModel", "Rows updated: $rowsUpdated")
        }
    }

    private fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.deleteToDo(toDo)
        }
    }

    var todoList: LiveData<List<ToDo>> = toDoRepository.getAllToDo()

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.OnAddToDo -> {
                val toDo = ToDo(
                    title = uiEvent.title,
                    description = uiEvent.description
                )
                insertToDo(toDo)
                Log.d("ToDoViewModel", "Added ToDo: $toDo with ID: ${toDo.id}")
            }
            is UiEvent.OnCompleteClick -> {
                updateToDo(uiEvent.toDo)
            }
            is UiEvent.OnDeleteClick -> {
                deleteToDo(uiEvent.toDo)
            }
            is UiEvent.OnUpdate -> {
                if (uiEvent.toDo.id != 0) {
                    updateToDo(uiEvent.toDo)
                    Log.d("ToDoViewModel", "Updated ToDo: ${uiEvent.toDo}")
                } else {
                    Log.d("ToDoViewModel", "ToDo ID is 0, not updating. ToDo details: ${uiEvent.toDo}")
                }
            }
        }
    }
}
