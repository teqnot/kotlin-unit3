package com.example.pract78.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pract78.domain.model.TodoItem
import com.example.pract78.presentation.ui.screen.TodoDetailScreen
import com.example.pract78.presentation.ui.screen.TodoListScreen
import com.example.pract78.presentation.viewmodel.TodoViewModel

const val ROUTE_HOME = "home"
const val ROUTE_DETAIL = "detail/{id}"

@Composable
fun TodoNavGraph(
    navController: NavHostController,
    viewModel: TodoViewModel,
    allTodos: List<TodoItem>
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME
    ) {
        composable(ROUTE_HOME) {
            TodoListScreen(
                viewModel = viewModel,
                onTodoClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }
        composable(
            route = ROUTE_DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            val todo = allTodos.find { it.id == id } ?: return@composable
            TodoDetailScreen(
                todo = todo,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}