package com.jhonjto.todolist.ui.add

import androidx.lifecycle.SavedStateHandle
import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.domain.TodoList
import com.jhonjto.usecases.TodoListById
import com.jhonjto.usecases.ToggleTodoListCheck
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

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
    @Named("todoListId")
    fun todoListIdProvider(stateHandle: SavedStateHandle): Int =
        stateHandle.get<Int>(AddTodoActivity.TODO_LIST)
            ?: throw IllegalStateException("TodoList Id not found in the state handle")
}