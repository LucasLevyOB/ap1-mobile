package com.example.ap1.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class TodoItem {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0;
    @ColumnInfo(name = "td_titulo")
    lateinit var titulo: String;
    @ColumnInfo(name = "td_descricao")
    lateinit var descricao: String;

    constructor(titulo: String, descricao: String) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}