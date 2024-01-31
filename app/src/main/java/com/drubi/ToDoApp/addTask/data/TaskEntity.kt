package com.drubi.ToDoApp.addTask.data

import androidx.room.PrimaryKey

data class TaskEntity(
    @PrimaryKey
    val id:Int,
    val task:String,
    val selected:Boolean = false
)