package com.drubi.ToDoApp.addTask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey
    val id:Int,
    val task:String,
    val selected:Boolean = false
)
