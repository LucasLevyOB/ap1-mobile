package com.example.ap1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.example.ap1.database.AppDatabase
import com.example.ap1.models.TodoItem
import com.google.android.material.snackbar.Snackbar

class CriarTodo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_todo)

        val btnCriarNovaTodo: Button = findViewById(R.id.btn_criar_todo);

        val appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "main-database")
            .enableMultiInstanceInvalidation()
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();

        val todoDao = appDatabase.todoDao();

        btnCriarNovaTodo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val edtTitulo = findViewById<EditText>(R.id.edt_titulo);
                val edtDescricao = findViewById<EditText>(R.id.edt_descricao);

                if (edtTitulo.text.isNullOrEmpty() || edtDescricao.text.isNullOrEmpty()) {
                    Snackbar.make(findViewById(R.id.edt_titulo), "Preencha todos os campos", Snackbar.LENGTH_LONG).show();
                    return;
                }

                try {
                    val titulo: String = edtTitulo.text.toString();
                    val descricao: String = edtDescricao.text.toString();

                    val todoItem  = TodoItem(titulo, descricao);

                    todoDao.insertAll(todoItem);

                    Snackbar.make(findViewById(R.id.btn_criar_nova_todo), "Item inserido com sucesso!", Snackbar.LENGTH_LONG).show();

                    val intent = Intent(applicationContext, MainActivity::class.java);
                    startActivity(intent);
                } catch (e: Exception) {
                    Snackbar.make(findViewById(R.id.edt_titulo), "Erro ao adicionar todo", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}