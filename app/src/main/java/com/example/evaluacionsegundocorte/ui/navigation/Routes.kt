package com.example.evaluacionsegundocorte.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreenRoute

@Serializable
object TaskList

@Serializable
data class TaskDetail(val taskId: Int? = null)