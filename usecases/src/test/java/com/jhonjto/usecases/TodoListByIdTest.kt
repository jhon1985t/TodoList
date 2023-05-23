package com.jhonjto.usecases

import com.jhonjto.data.repositories.TodoListRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TodoListByIdTest {
    @Mock
    lateinit var todoListRepository: TodoListRepository

    lateinit var todoListById: TodoListById

    @Before
    fun setUp() {
        todoListById = TodoListById(todoListRepository)
    }

    @Test
    fun `invoke calls get todo list by id repository`() {
        runBlocking {
            whenever(todoListRepository.findById(6)).thenReturn(mockedTodoList)

            val result = todoListById.invoke(6)

            Assert.assertEquals(mockedTodoList, result)
        }
    }
}