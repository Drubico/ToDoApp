package com.drubi.ToDoApp.addTask.ui.model

data class TaskModel(
    val id:Int = System.currentTimeMillis().hashCode(),
    val task:String,
    val selected:Boolean = false
)
