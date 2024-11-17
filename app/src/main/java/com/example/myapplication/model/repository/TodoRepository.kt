package com.example.myapplication.model.repository

import com.example.myapplication.model.Todo
import com.example.myapplication.model.TodoDataBase

class TodoRepository(private val dataBase: TodoDataBase){
    suspend fun insert(todo: Todo)=dataBase.getTodoDao().addTodo(todo)
    suspend fun delete(todo: Todo)=dataBase.getTodoDao().deleteTodo(todo)
    fun getAllTodo()=dataBase.getTodoDao().getAllTodo()
}