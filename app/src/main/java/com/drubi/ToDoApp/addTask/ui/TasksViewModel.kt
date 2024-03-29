package com.drubi.ToDoApp.addTask.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drubi.ToDoApp.addTask.domain.AddTaskUseCase
import com.drubi.ToDoApp.addTask.domain.DeleteTaskUseCase
import com.drubi.ToDoApp.addTask.domain.GetTaskUseCase
import com.drubi.ToDoApp.addTask.domain.UpdateTaskUseCase
import com.drubi.ToDoApp.addTask.ui.TaskUiState.Success
import com.drubi.ToDoApp.addTask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel
@Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    val uiState:StateFlow<TaskUiState> = getTaskUseCase().map(
        ::Success
    ).catch {
        TaskUiState.Error(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        TaskUiState.Loading
    )

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    // no usar live data en recycleview
//    private val _tasks = mutableStateListOf<TaskModel>()
//    val tasks: List<TaskModel> = _tasks
    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        onDialogClose()
        viewModelScope.launch {
            addTaskUseCase(
                TaskModel(
                    task = task
                )
            )
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
//        val index = _tasks.indexOf(taskModel)
//        _tasks[index] = _tasks[index].let {
//            it.copy(selected = !it.selected)
//        }
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        // _tasks.remove(taskModel) -> no funciona porque por el copy no es el mismo objeto
//        val task = _tasks.find { it.id == taskModel.id }
//        _tasks.remove(task)
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }
    }


}