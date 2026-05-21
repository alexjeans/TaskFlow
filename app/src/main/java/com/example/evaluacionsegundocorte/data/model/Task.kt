package com.example.evaluacionsegundocorte.data.model

data class Task(
    val id: Int,
    val title: String,
    val priority: String = "Baja",
    val completed: Boolean = false
)