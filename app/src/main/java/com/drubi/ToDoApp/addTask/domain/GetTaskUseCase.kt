package com.drubi.ToDoApp.addTask.domain

import com.drubi.ToDoApp.addTask.data.TaskRepository
import com.drubi.ToDoApp.addTask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.task
}