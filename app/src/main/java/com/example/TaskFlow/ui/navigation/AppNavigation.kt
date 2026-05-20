package com.example.TaskFlow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.TaskFlow.ui.screen.TaskDetailScreen
import com.example.TaskFlow.ui.screen.TaskListScreen

@Composable
fun AppNavigation(modifier: Modifier)
{
    val navController = rememberNavController()

    NavHost(navController = navController
        , startDestination = TaskList)
    {
        composable<TaskList>
        {
            TaskListScreen(navController = navController)
        }
        composable<TaskDetail>{ backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()
            TaskDetailScreen(navController = navController,
                taskId = route.taskId)
        }

    }
}