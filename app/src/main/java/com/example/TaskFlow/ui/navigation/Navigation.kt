package com.example.TaskFlow.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object TaskList
@Serializable
data class TaskDetail(
    val taskId : Int
)