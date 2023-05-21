package com.jhonjto.todolist.ui.main

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.usecases.GetAllTodoList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getAllTodoListProvider(todoListRepository: TodoListRepository) =
        GetAllTodoList(todoListRepository)
}
