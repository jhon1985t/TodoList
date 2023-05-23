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
class DeleteByIdTest {
    @Mock
    lateinit var todoListRepository: TodoListRepository

    lateinit var deleteById: DeleteById

    @Before
    fun setUp() {
        deleteById = DeleteById(todoListRepository)
    }

    @Test
    fun `invoke calls delete by id todo list repository`() {
        runBlocking {
            whenever(todoListRepository.deleteById(6)).thenReturn(Unit)

            val result = deleteById.invoke(6)

            Assert.assertEquals(Unit, result)
        }
    }
}