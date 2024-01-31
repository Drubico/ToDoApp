package com.drubi.ToDoApp.addTask.ui.model

data class TaskModel(
    val id:Long = System.currentTimeMillis(),
    val task:String,
    val selected:Boolean = false
)
