package com.example.pract78.presentation.viewmodel

import androidx.lifecycle.*
import com.example.pract78.domain.model.TodoItem
import com.example.pract78.domain.usecase.GetTodosUseCase
import com.example.pract78.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    init {
        viewModelScope.launch {
            _todos.value = getTodosUseCase()
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            _todos.value = _todos.value.map { if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it }
        }
    }
}