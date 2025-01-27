package com.example.loginfirebaseapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginfirebaseapp.pages.HomePage
import com.example.loginfirebaseapp.pages.LoginPage
import com.example.loginfirebaseapp.pages.SignupPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
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

    })
}