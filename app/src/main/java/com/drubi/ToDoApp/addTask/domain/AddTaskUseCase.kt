package com.drubi.ToDoApp.addTask.domain

import com.drubi.ToDoApp.addTask.data.TaskRepository
import com.drubi.ToDoApp.addTask.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase
@Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskModel: TaskModel) = taskRepository.add(taskModel)
}