package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Todo
import com.example.myapplication.model.TodoDataBase
import com.example.myapplication.model.repository.TodoRepository
import com.example.myapplication.viewModel.TodoViewModel
import com.example.myapplication.viewModel.TodoViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = TodoDataBase.getInstance(this)
        val repository = TodoRepository(db)
        val factory=TodoViewModelFactory(repository)
        val todoViewModel=ViewModelProvider(this,factory)[TodoViewModel::class.java]
        val adapter=TodoAdapter(this, listOf(),todoViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter
        todoViewModel.allTodos.observe(this) {
            adapter.updateData(it)
        }
        binding.addTodo.setOnClickListener {
            val todo=binding.todo.text.toString()
            if(todo.isNotEmpty()){
                Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()
                todoViewModel.insertTodo(Todo(0,todo))
                binding.todo.text?.clear()
                binding.todo.isFocusable=false
            }else{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}