package com.example.loginfirebaseapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginfirebaseapp.pages.AlertDialogPage
import com.example.loginfirebaseapp.pages.HomePage
import com.example.loginfirebaseapp.pages.LoginPage
import com.example.loginfirebaseapp.pages.PartialBottomSheet
import com.example.loginfirebaseapp.pages.SignupPage
import com.example.loginfirebaseapp.roomDB.AppDatabase
import com.example.loginfirebaseapp.ui.screens.CategoryScreen
import com.example.loginfirebaseapp.ui.screens.SubtaskScreen
import com.example.loginfirebaseapp.ui.screens.TaskScreen
import com.example.loginfirebaseapp.ui.viewmodel.CategoryViewModel
import com.example.loginfirebaseapp.ui.viewmodel.SubtaskViewModel
import com.example.loginfirebaseapp.ui.viewmodel.TaskViewModel

@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel, 
    db: AppDatabase, 
    categoryViewModel: CategoryViewModel,
    taskViewModel: TaskViewModel,
    subtaskViewModel: SubtaskViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginPage(modifier = Modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier = Modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier = Modifier, navController, authViewModel)
        }
        composable("bottomSheet") {
            PartialBottomSheet(modifier = Modifier, navController, db = db)
        }

        composable("alertDialog/{nameOfUser}/{emailOfUser}") { backStackEntry ->
            val nameOfUser = backStackEntry.arguments?.getString("nameOfUser")?: "Anonymous Users"
            val emailOfUser = backStackEntry.arguments?.getString("emailOfUser")?: "Email of User is not provided."
            AlertDialogPage(modifier = Modifier, navController, nameOfUser, emailOfUser)
        }

        composable("categoryScreen") {
            CategoryScreen(
                categoryViewModel,
                onCategoryClick = { categoryId ->
                    navController.navigate("taskScreen/{$categoryId}")
                }
            )
        }

        composable("taskScreen/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()?: return@composable
            TaskScreen(
                taskViewModel,
                categoryId = categoryId,
                onTaskClick = { taskId ->
                    navController.navigate("subtaskScreen/{$taskId}")
                }
            )
        }
        composable("subtaskScreen/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()?: return@composable
            SubtaskScreen(
                subtaskViewModel,
                taskId = taskId
            )
        }




    })
}