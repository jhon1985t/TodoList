package com.jhonjto.todolist.di

import com.jhonjto.data.repositories.TodoListRepository
import com.jhonjto.data.source.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun todoListRepositoryProvider(
        localDataSource: LocalDataSource
    ) = TodoListRepository(localDataSource)
}
