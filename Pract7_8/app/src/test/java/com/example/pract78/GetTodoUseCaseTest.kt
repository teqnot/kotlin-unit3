package com.example.pract78

import com.example.pract78.domain.model.TodoItem
import com.example.pract78.domain.repository.TodoRepository
import com.example.pract78.domain.usecase.GetTodosUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetTodoUseCaseTest {
    @Test
    fun `invoke returns 3 todos`() = runTest {
        val mockRepo = mock<TodoRepository> {
            onBlocking { getTodos() } doReturn listOf(
                TodoItem(1, "A", "a", false),
                TodoItem(2, "B", "b", true),
                TodoItem(3, "C", "c", false)
            )
        }
        val useCase = GetTodosUseCase(mockRepo)
        val result = useCase()
        assertEquals(3, result.size)
    }
}