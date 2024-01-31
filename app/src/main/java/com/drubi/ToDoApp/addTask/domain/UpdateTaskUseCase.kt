package com.drubi.ToDoApp.addTask.domain

import com.drubi.ToDoApp.addTask.data.TaskRepository
import com.drubi.ToDoApp.addTask.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {

    suspend operator fun invoke(taskModel: TaskModel) {
        tasksRepository.update(taskModel)
    }
}