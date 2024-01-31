package com.drubi.ToDoApp.addTask.data

import com.drubi.ToDoApp.addTask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository
@Inject constructor(
    private val taskDao: TaskDao
) {

    val task: Flow<List<TaskModel>> = taskDao.getTask().map {
        it.map { taskEntity ->
            TaskModel(taskEntity.id, taskEntity.task, taskEntity.selected)
        }
    }
    suspend fun add(taskModel: TaskModel){
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }
}