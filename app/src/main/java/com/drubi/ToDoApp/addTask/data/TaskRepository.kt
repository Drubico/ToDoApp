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
        taskDao.addTask(taskModel.toEntity())
    }

    suspend fun update(taskModel: TaskModel){
        taskDao.updateTask(taskModel.toEntity())
    }

    suspend fun delete(taskModel: TaskModel){
        taskDao.deleteTask(taskModel.toEntity())
    }
}

fun TaskModel.toEntity() = TaskEntity(this.id, this.task, this.selected)