package com.example.ap1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ap1.models.TodoItem

@Dao
interface TodoDao {
    @Insert
    fun insertAll(vararg todos: TodoItem);

    @Delete
    fun delete(pessoa: TodoItem): Int;

    @Query("SELECT * FROM todo")
    fun listAll(): List<TodoItem>;
}