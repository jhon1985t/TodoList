package com.jhonjto.todolist.di

import android.app.Application
import androidx.room.Room
import com.jhonjto.data.source.LocalDataSource
import com.jhonjto.todolist.data.database.RoomDataSource
import com.jhonjto.todolist.data.database.TodoListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        TodoListDatabase::class.java,
        "todo_list-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: TodoListDatabase):
            LocalDataSource = RoomDataSource(db)
}
