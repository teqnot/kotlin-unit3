package com.example.pract78

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.pract78.data.repository.TodoRepositoryImpl
import com.example.pract78.domain.usecase.GetTodosUseCase
import com.example.pract78.domain.usecase.ToggleTodoUseCase
import com.example.pract78.navigation.TodoNavGraph
import com.example.pract78.presentation.ui.theme.Pract78Theme
import com.example.pract78.presentation.viewmodel.TodoViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = TodoRepositoryImpl(this)
        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)

        val allTodos = runBlocking { getTodosUseCase() }

        setContent {
            Pract78Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: TodoViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                @Suppress("UNCHECKED_CAST")
                                return TodoViewModel(getTodosUseCase, toggleTodoUseCase) as T
                            }
                        }
                    )
                    TodoNavGraph(navController, viewModel, allTodos)
                }
            }
        }
    }
}