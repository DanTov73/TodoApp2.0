package com.example.todoapp20.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp20.ui.fragments.models.TaskModel

@Dao
interface TaskDao {
    @Insert
    fun insert(taskModel: TaskModel)

    @Query("SELECT * FROM table_name")
    fun getAll(): LiveData<List<TaskModel>>

    @Update
    fun updateData(taskModel: TaskModel)

    @Delete
    fun deleteData(taskModel: TaskModel)
}