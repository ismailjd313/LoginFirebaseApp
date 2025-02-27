package com.example.loginfirebaseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginfirebaseapp.roomDB.AppDatabase
import com.example.loginfirebaseapp.ui.theme.LoginFirebaseAppTheme
import com.example.loginfirebaseapp.ui.viewmodel.CategoryViewModel
import com.example.loginfirebaseapp.ui.viewmodel.SubtaskViewModel
import com.example.loginfirebaseapp.ui.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            context = applicationContext,
            klass = AppDatabase::class.java,
            name = "app-database"
        ).build()

        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        // ViewModel Initialization
        val categoryViewModel: CategoryViewModel by viewModels()
        val taskViewModel: TaskViewModel by viewModels()
        val subtaskViewModel: SubtaskViewModel by viewModels()

        setContent {
            LoginFirebaseAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel,
                        db = db,
                        categoryViewModel,
                        taskViewModel,
                        subtaskViewModel
                    )
                }
            }
        }
    }
}

