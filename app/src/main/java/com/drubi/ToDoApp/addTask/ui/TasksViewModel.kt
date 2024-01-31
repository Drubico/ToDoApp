package com.drubi.ToDoApp.addTask.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drubi.ToDoApp.addTask.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel
@Inject constructor() : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    // no usar live data en recycleview
    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks
    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(it: String) {
        Log.i("TasksViewModel", "onTaskAdded: $it")
        _tasks.add(TaskModel(
            task = it
        ))
        onDialogClose()
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        // _tasks.remove(taskModel) -> no funciona porque por el copy no es el mismo objeto
        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(task)
    }


}