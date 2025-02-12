package com.example.loginfirebaseapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginfirebaseapp.AuthState
import com.example.loginfirebaseapp.AuthViewModel

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Column (
        modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Home Page", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Text("If you want to check the Model Bottom Sheet")
        TextButton(onClick = {
            navController.navigate("bottomSheet")
        }) {
            Text("Click Here!")
        }

        OutlinedButton(onClick = {
            navController.navigate("categoryScreen")
        }) {
            Text(
                text = "Go to Categories Screen"
            )
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(0.6f),
            thickness = 2.dp,
            color = Color.LightGray
        )
        Text("If you want to check the Model Bottom Sheet", textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(8.dp))
        VerticalDivider(modifier = Modifier.fillMaxSize(0.1f), thickness = 4.dp, color = Color.Magenta)
        Spacer(modifier = Modifier.height(8.dp))
        VerticalDivider(modifier = Modifier.fillMaxSize(0.1f), thickness = 4.dp, color = Color.Magenta)

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = {
                authViewModel.signout()
            }
        ) {
            Text("Sign out")


        }
    }
    
}