package com.drubi.ToDoApp.addTask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.drubi.ToDoApp.addTask.ui.model.TaskModel


@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val showDialog by tasksViewModel.showDialog.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {
        AddTasksDialog(
            show = showDialog,
            onDismiss = {
                tasksViewModel.onDialogClose()
            },
            onTaskAdded = {
                tasksViewModel.onTaskCreated(it)
            })
        FabDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
        TaskList(tasksViewModel)
    }
}

@Composable
fun TaskList(tasksViewModel: TasksViewModel) {
    val myTasks: List<TaskModel> = tasksViewModel.tasks
    LazyColumn() {
        items(myTasks, key = { it.id }) { task ->
            ItemTask(taskModel = task, tasksViewModel = tasksViewModel)
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp).pointerInput(Unit){
               detectTapGestures (
                   onLongPress = {
                       tasksViewModel.onItemRemove(taskModel)
                   }
               )
            },
        elevation = CardDefaults.cardElevation(
            disabledElevation = 8.dp,
            draggedElevation = 8.dp,
            focusedElevation = 8.dp,
            hoveredElevation = 8.dp,
            pressedElevation = 8.dp,
        )
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task, modifier = Modifier
                    .weight(1f)

            )
            Checkbox(checked = taskModel.selected, onCheckedChange = {
                tasksViewModel.onCheckBoxSelected(taskModel)
            })
        }
    }
}

@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(
        onClick = {
            tasksViewModel.onShowDialogClick()
        },
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTasks by rememberSaveable {
        mutableStateOf("")
    }
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Añadir tarea",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                TextField(
                    value = myTasks,
                    onValueChange = { myTasks = it },
                    singleLine = true,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = {
                        onTaskAdded(myTasks)
                        myTasks = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Añadir")
                }
            }

        }
    }
}