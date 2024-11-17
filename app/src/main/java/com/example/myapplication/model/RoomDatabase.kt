package com.example.myapplication.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val todo:String
)
@Dao
interface TodoDao{
    @Query("SELECT * FROM todo_table")
    fun getAllTodo():LiveData<List<Todo>>
    @Insert
    suspend fun addTodo(todo: Todo)
    @Delete
    suspend fun deleteTodo(todo: Todo)
}
@Database(entities = [Todo::class], version = 1)
abstract class TodoDataBase:RoomDatabase(){
    companion object{
        @Volatile
        private var instance:TodoDataBase?=null
        private val LOCK=Any()
        fun getInstance(context: Context):TodoDataBase{
            return instance?: synchronized(LOCK){
                instance?: createDatabase(context).also { instance=it }
            }
        }
        private fun createDatabase(context: Context):TodoDataBase{
            return Room.databaseBuilder(context.applicationContext,
                TodoDataBase::class.java,
                "TODO_DATABASE"
                ).build()
        }
    }
    abstract fun getTodoDao():TodoDao
}