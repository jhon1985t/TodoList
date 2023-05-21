package com.jhonjto.todolist.data.database

import androidx.room.*

@Dao
interface TodoListDao {

    @Query("SELECT * FROM TodoListEntities ORDER BY date DESC")
    fun getAll(): List<TodoListEntities>

    @Query("SELECT * FROM TodoListEntities WHERE id = :id")
    fun findById(id: Int): TodoListEntities

    @Query("SELECT COUNT(id) FROM TodoListEntities")
    fun todoListCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todoListEntities: TodoListEntities)

    @Update
    fun updateTodoList(todoListEntities: TodoListEntities)

    @Query("DELETE FROM TodoListEntities WHERE id = :id")
    fun deleteByUserId(id: Int)

    @Query("DELETE FROM TodoListEntities")
    fun deleteAll()
}