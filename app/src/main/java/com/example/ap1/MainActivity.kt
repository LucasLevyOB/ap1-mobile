package com.example.ap1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.ap1.dao.TodoDao
import com.example.ap1.database.AppDatabase
import com.example.ap1.models.TodoItem

class MainActivity : AppCompatActivity() {
    private var todoList = ArrayList<TodoItem>();
    lateinit var appDatabase: AppDatabase;
    lateinit var todoDao: TodoDao;
    lateinit var todoAdapter: TodoItemAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "main-database")
            .enableMultiInstanceInvalidation()
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();

        val recyclerView = findViewById<RecyclerView>(R.id.rv_todo_list);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        todoAdapter = TodoItemAdapter(todoList);
        recyclerView.adapter = todoAdapter;

        todoDao = appDatabase.todoDao();

        val todoDoBd: List<TodoItem> = todoDao.listAll();
        for (todo in todoDoBd) {
            todoList.add(todo);
        }

        todoAdapter.notifyDataSetChanged();

        val btnCriarNovaTodo: Button = findViewById(R.id.btn_nova_todo);

        btnCriarNovaTodo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(applicationContext, CriarTodo::class.java);
                startActivity(intent);
            }
        });
    }

    override fun onStart() {
        super.onStart()

        todoList.clear();
        val todoDoBd: List<TodoItem> = todoDao.listAll();
        for (todo in todoDoBd) {
            todoList.add(todo);
        }

        todoAdapter.notifyDataSetChanged();
    }
}