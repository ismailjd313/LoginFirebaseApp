package com.example.loginfirebaseapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loginfirebaseapp.model.Task
import com.example.loginfirebaseapp.ui.viewmodel.TaskViewModel


@Composable
fun TaskScreen(viewModel: TaskViewModel, categoryId: Int, onTaskClick: (Int) -> Unit) {
    val tasks by viewModel.getTasksByCategory(categoryId).collectAsState(initial = emptyList())
    var taskName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (taskName.isNotEmpty()) {
                    viewModel.insert(
                        Task(
                            title = taskName,
                            categoryOwnerId = categoryId,
                            description = null,
                            dueDate = null
                        )
                    )
                    taskName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = task.title,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton (onClick = { onTaskClick(task.taskId) }) {
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Open Subtasks")
                        }

                        IconButton(onClick = { viewModel.delete(task) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}