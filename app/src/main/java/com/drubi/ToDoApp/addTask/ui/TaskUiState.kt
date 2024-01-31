package com.drubi.ToDoApp.addTask.ui

import com.drubi.ToDoApp.addTask.ui.model.TaskModel

sealed interface TaskUiState {
    object Loading : TaskUiState
    data class Error(val throwable:Throwable ) : TaskUiState
    data class Success(val data: List<TaskModel>) : TaskUiState
}