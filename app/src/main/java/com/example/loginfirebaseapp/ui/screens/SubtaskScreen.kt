package com.example.loginfirebaseapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loginfirebaseapp.model.Subtask
import com.example.loginfirebaseapp.ui.viewmodel.SubtaskViewModel

@Composable
fun SubtaskScreen(viewModel: SubtaskViewModel, taskId: Int) {
    val subtasks by viewModel.getSubtasksByTask(taskId).collectAsState(initial = emptyList())
    var subtaskName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = subtaskName,
            onValueChange = { subtaskName = it },
            label = { Text("Subtask Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (subtaskName.isNotEmpty()) {
                    viewModel.insert(Subtask(title = subtaskName, taskOwnerId = taskId))
                    subtaskName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Subtask")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(subtasks) { subtask ->
                Card(
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
                            text = subtask.title,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = { viewModel.delete(subtask) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}