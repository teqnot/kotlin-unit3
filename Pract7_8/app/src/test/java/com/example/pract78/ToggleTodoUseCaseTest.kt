package com.example.pract78

import com.example.pract78.domain.repository.TodoRepository
import com.example.pract78.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class ToggleTodoUseCaseTest {
    @Test
    fun `invoke calls repository toggleTodo`() = runTest {
        val mockRepo = mock<TodoRepository>()
        val useCase = ToggleTodoUseCase(mockRepo)
        useCase(42)
        verifyBlocking(mockRepo) { toggleTodo(42) }
    }
}