package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.myapplication.model.Todo
import com.example.myapplication.model.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository):ViewModel(){
    val allTodos: LiveData<List<Todo>> = repository.getAllTodo()
    fun insertTodo(todo: Todo)= CoroutineScope(Dispatchers.Main).launch {
        repository.insert(todo)
    }
    fun deleteTodo(todo: Todo)=CoroutineScope(Dispatchers.Main).launch {
        repository.delete(todo)
    }
}
class TodoViewModelFactory(private val repository: TodoRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T:ViewModel> create(modelClass:Class<T>):T{
        return TodoViewModel(repository) as T
    }
}