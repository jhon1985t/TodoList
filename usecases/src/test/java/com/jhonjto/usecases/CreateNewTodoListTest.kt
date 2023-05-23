package com.jhonjto.usecases

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.domain.TodoList
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class CreateNewTodoListTest {
    @Mock
    lateinit var todoListRepository: TodoListRepository

    lateinit var createNewTodoList: CreateNewTodoList

    @Before
    fun setUp() {
        createNewTodoList = CreateNewTodoList(todoListRepository)
    }

    @Test
    fun `invoke calls create all todo list repository`() {
        runBlocking {
            whenever(todoListRepository.saveTodoList(mockedTodoList)).thenReturn(Unit)

            val result = createNewTodoList.invoke(mockedTodoList)

            assertEquals(Unit, result)
        }
    }
}

val mockedTodoList = TodoList(
    id = 6,
    title = "Test",
    note = "Test about room db",
    date = LocalDateTime.now(),
    isComplete = true
)