package com.example.ap1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ap1.models.TodoItem

class TodoItemAdapter(private val todoList: ArrayList<TodoItem>): RecyclerView.Adapter<TodoItemAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return todoList.size;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitulo: TextView = view.findViewById(R.id.tv_titulo);
        val tvDescricao: TextView = view.findViewById(R.id.tv_descricao);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false);

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitulo.setText(todoList[position].titulo);
        holder.tvDescricao.setText(todoList[position].descricao);
    }
}