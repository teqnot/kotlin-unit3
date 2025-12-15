package com.example.pract78.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pract78.presentation.ui.component.TodoItemCard
import com.example.pract78.presentation.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    onTodoClick: (Int) -> Unit
) {
    val todos by viewModel.todos.collectAsStateWithLifecycle()

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag("home_screen")
        ) {
            items(todos) { todo ->
                TodoItemCard(
                    todo = todo,
                    onToggle = { viewModel.toggleTodo(it) },
                    onClick = { onTodoClick(todo.id) }
                )
            }
        }
    }
}