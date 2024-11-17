package com.example.myapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Todo
import com.example.myapplication.viewModel.TodoViewModel

class TodoAdapter(private val context: Context, private var dataList: List<Todo>, private val todoViewModel: TodoViewModel):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(view: View):RecyclerView.ViewHolder(view){
        val todoTxt=view.findViewById<TextView>(R.id.todo)
        val deleteBtn=view.findViewById<ImageButton>(R.id.delete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.custom_todo,parent,false)
        return TodoViewHolder(view)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       val currentTodo=dataList[position]
        holder.todoTxt.text=currentTodo.todo
        holder.deleteBtn.setOnClickListener {
            todoViewModel.deleteTodo(currentTodo)
        }
    }
    fun updateData(newDataList: List<Todo>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

}