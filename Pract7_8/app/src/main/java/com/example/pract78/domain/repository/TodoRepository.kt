package com.example.pract78.domain.repository

import com.example.pract78.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}