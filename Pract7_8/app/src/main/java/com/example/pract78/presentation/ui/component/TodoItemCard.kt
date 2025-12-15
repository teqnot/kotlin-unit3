package com.example.pract78.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pract78.domain.model.TodoItem
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.material3.*
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun TodoItemCard(
    todo: TodoItem,
    onToggle: (Int) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .testTag("todo_item_${todo.id}"),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = { onToggle(todo.id) },
                modifier = Modifier.testTag("checkbox_${todo.id}")
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                    modifier = Modifier
                        .testTag("title_${todo.id}")
                )
                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}