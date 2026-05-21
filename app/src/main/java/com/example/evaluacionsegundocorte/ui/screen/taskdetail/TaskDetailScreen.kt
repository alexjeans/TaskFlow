package com.example.evaluacionsegundocorte.ui.screen.taskdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.evaluacionsegundocorte.data.model.Task
import com.example.evaluacionsegundocorte.ui.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController,
    taskId: Int?,
    viewModel: TaskViewModel = viewModel()
) {
    LaunchedEffect(taskId) {
        viewModel.loadTaskById(taskId)
    }

    val id by viewModel.id.collectAsState()
    val title by viewModel.title.collectAsState()
    val priority by viewModel.priority.collectAsState()
    val completed by viewModel.completed.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = if (taskId == null) "Nueva Tarea" else "Editar Tarea",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = id,
                    onValueChange = { viewModel.onIdChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("ID de Tarea") },
                    leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) }, // Icono básico Info
                    singleLine = true,
                    enabled = taskId == null
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { viewModel.onTitleChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Título de la tarea") },
                    leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }, // Icono básico Edit
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = priority,
                    onValueChange = { viewModel.onPriorityChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Prioridad (Alta, Media, Baja)") },
                    leadingIcon = { Icon(Icons.Default.List, contentDescription = null) }, // Icono básico List
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null) // Icono básico Close
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cancelar")
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            val finalId = id.toIntOrNull() ?: (10..1000).random()

                            if (title.isNotBlank()) {
                                viewModel.addTask(
                                    Task(
                                        id = finalId,
                                        title = title,
                                        priority = priority,
                                        completed = completed
                                    )
                                )
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Icon(Icons.Default.Done, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Guardar")
                    }
                }
            }
        }
    }
}