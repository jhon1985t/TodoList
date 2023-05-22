package com.jhonjto.todolist.ui.add

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.usecases.CreateNewTodoList
import com.jhonjto.usecases.DeleteById
import com.jhonjto.usecases.TodoListById
import com.jhonjto.usecases.ToggleTodoListCheck
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AddTodoActivityModule {

    @Provides
    fun finTodoListByIdProvider(todoListRepository: TodoListRepository) =
        TodoListById(todoListRepository)

    @Provides
    fun toggleTodoListCheckProvider(todoListRepository: TodoListRepository) =
        ToggleTodoListCheck(todoListRepository)

    @Provides
    fun createNewTodoListProvider(todoListRepository: TodoListRepository) =
        CreateNewTodoList(todoListRepository)

    @Provides
    fun deleteByIdProvider(todoListRepository: TodoListRepository) =
        DeleteById(todoListRepository)
}
