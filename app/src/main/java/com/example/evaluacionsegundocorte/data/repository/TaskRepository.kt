package com.example.evaluacionsegundocorte.data.repository

import com.example.evaluacionsegundocorte.data.model.Task

class TaskRepository {
    private val tasks = mutableListOf(
        Task(1, "Configurar repositorio", "Alta", true),
        Task(2, "Implementar StateFlow", "Alta", false),
        Task(3, "Diseñar SplashScreen", "Media", false)
    )

    fun getTasks(): List<Task> = tasks.toList()

    fun addTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task // Actualiza la tarea si ya existe el ID
        } else {
            tasks.add(task) // Agrega una nueva si el ID no existe
        }
    }

    fun getTaskId(id: Int): Task? = tasks.find { it.id == id }

    fun removeTask(task: Task) = tasks.remove(task)

    fun toggleTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task.copy(completed = !task.completed)
        }
    }
}