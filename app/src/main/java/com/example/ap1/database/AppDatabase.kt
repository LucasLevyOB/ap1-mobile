package com.example.ap1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ap1.dao.TodoDao
import com.example.ap1.models.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao;
}