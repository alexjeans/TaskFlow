package com.example.evaluacionsegundocorte.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.evaluacionsegundocorte.data.model.Task
import com.example.evaluacionsegundocorte.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    val id = MutableStateFlow("")
    val title = MutableStateFlow("")
    val priority = MutableStateFlow("Baja")
    val completed = MutableStateFlow(false)

    init {
        loadAllTasks()
    }

    fun onIdChange(newId: String) { id.value = newId }
    fun onTitleChange(newTitle: String) { title.value = newTitle }
    fun onPriorityChange(newPriority: String) { priority.value = newPriority }
    fun onCompletedChange(newCompleted: Boolean) { completed.value = newCompleted }

    private fun loadAllTasks() {
        _tasks.value = repository.getTasks()
    }

    fun loadTaskById(taskId: Int?) {
        if (taskId == null) {
            clearForm()
        } else {
            val task = repository.getTaskId(taskId)
            task?.let {
                id.value = it.id.toString()
                title.value = it.title
                priority.value = it.priority
                completed.value = it.completed
            }
        }
    }

    fun addTask(task: Task) {
        repository.addTask(task)
        loadAllTasks()
    }

    fun removeTask(task: Task) {
        repository.removeTask(task)
        loadAllTasks()
    }

    fun toggleTask(task: Task) {
        repository.toggleTask(task)
        loadAllTasks()
    }

    fun clearForm() {
        id.value = ""
        title.value = ""
        priority.value = "Baja"
        completed.value = false
    }
}