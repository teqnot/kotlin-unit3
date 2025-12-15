package com.example.pract78.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.pract78.domain.model.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todo: TodoItem,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали задачи") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад", modifier = Modifier.testTag("back_button"))
                    }
                },
                modifier = Modifier.testTag("detail_screen")
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineSmall,
                textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = todo.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text("Статус: ")
                Text(
                    text = if (todo.isCompleted) "Выполнено" else "Не выполнено",
                    color = if (todo.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }
        }
    }
}