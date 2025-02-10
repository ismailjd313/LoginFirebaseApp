package com.example.loginfirebaseapp.pages

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AlertDialogPage(modifier: Modifier = Modifier, navController: NavController,  nameOfUser : String, emailOfUser : String) {
    // ...
    var openAlertDialog by remember { mutableStateOf(false) }

    val names = mutableListOf("ismail", "javeria", "haleema", "faizan")

    names.forEach {name ->
        Text(text = name)

    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "The Name of user is: $nameOfUser")
        Text(text = "The Email of user is: $emailOfUser")

        val context = LocalContext.current
        val url = "https://www.amazon.com/Redragon-S101-Keyboard-Ergonomic-Programmable/dp/B00NLZUM36/ref=sr_1_2?_encoding=UTF8&content-id=amzn1.sym.12129333-2117-4490-9c17-6d31baf0582a&dib=eyJ2IjoiMSJ9.GoHw03VyLhlV5P1QkaN6Dfm98i_S1bODJ_Fo8IlAfcaqy4o-0Md3CzF9lzi_N2DjhFOXn7jWAZu9EGtj4uGuNKqhD-rQK03-dGisW8gHy0ZDeSg8Yd2tYzxTUUiosxFqeS6_fjCLZoxmZLJFu_shIrMP6KMIuCZvKOmH-M8drhhgt4yI_QJ-w8UGZu0VZizmPtgLTlC6EoSCxLvXOCTVmZc3sBrfKtES5i0SuOWFSQE.y5OQz7yNwtMgHA7Vkp0kkF1IviNNt0r0UuHqiUomrLg&dib_tag=se&keywords=gaming%2Bkeyboard&pd_rd_r=a1d058ce-9bec-459b-9edb-94176d6658a3&pd_rd_w=74Aaq&pd_rd_wg=RAs4Q&qid=1738953128&sr=8-2&th=1"  // Replace with your actual link

        ClickableText(
            text = AnnotatedString("Buy on Amazon"),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = {
            openAlertDialog = true
        }) {
            Icon(Icons.Filled.Warning, "Alert Dialog Box")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Open Alert Dialog Box")
        }
    }


    // ...
    when {
        // ...
        openAlertDialog -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog= false
                    println("Confirmation registered") // Add logic here to handle confirmation.
                },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info
            )
        }
    }
}



@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}
