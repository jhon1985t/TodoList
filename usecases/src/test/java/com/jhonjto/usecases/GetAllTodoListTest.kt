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
class GetAllTodoListTest {
    @Mock
    lateinit var todoListRepository: TodoListRepository

    lateinit var getAllTodoList: GetAllTodoList

    @Before
    fun setUp() {
        getAllTodoList = GetAllTodoList(todoListRepository)
    }

    @Test
    fun `invoke calls get all todo list repository`() {
        runBlocking {
            whenever(todoListRepository.getTodoList()).thenReturn(listOf(mockedTodoList))

            val result = getAllTodoList.invoke()

            Assert.assertEquals(listOf(mockedTodoList), result)
        }
    }
}