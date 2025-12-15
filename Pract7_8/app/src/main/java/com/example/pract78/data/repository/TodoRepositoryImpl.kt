package com.example.pract78.data.repository

import android.content.Context
import com.example.pract78.data.local.TodoJsonDataSource
import com.example.pract78.data.model.TodoItemDto
import com.example.pract78.domain.model.TodoItem
import com.example.pract78.domain.repository.TodoRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TodoRepositoryImpl(context: Context) : TodoRepository {
    private val dataSource = TodoJsonDataSource(context)
    private val mutex = Mutex()

    private var todos: MutableList<TodoItem> = dataSource.getTodos().map { it.toDomain() }.toMutableList()

    override suspend fun getTodos(): List<TodoItem> = todos.toList()

    override suspend fun toggleTodo(id: Int) {
        mutex.withLock {
            val index = todos.indexOfFirst { it.id == id }
            if (index != -1) {
                todos[index] = todos[index].copy(isCompleted = !todos[index].isCompleted)
            }
        }
    }

    private fun TodoItemDto.toDomain() = TodoItem(id, title, description, isCompleted)
}