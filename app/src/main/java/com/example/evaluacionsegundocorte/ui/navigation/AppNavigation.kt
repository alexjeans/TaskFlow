package com.example.evaluacionsegundocorte.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.evaluacionsegundocorte.ui.screen.SplashScreen
import com.example.evaluacionsegundocorte.ui.screen.TaskDetailScreen
import com.example.evaluacionsegundocorte.ui.screen.TaskListScreen
import com.example.evaluacionsegundocorte.ui.viewmodel.TaskViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val sharedViewModel: TaskViewModel = viewModel()

    NavHost(navController = navController, startDestination = SplashScreenRoute) {
        composable<SplashScreenRoute> {
            SplashScreen(navController = navController)
        }
        composable<TaskList> {
            TaskListScreen(
                navController = navController,
                viewModel = sharedViewModel
            )
        }
        composable<TaskDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()
            TaskDetailScreen(
                navController = navController,
                taskId = route.taskId,
                viewModel = sharedViewModel
            )
        }
    }
}