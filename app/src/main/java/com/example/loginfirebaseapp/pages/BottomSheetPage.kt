package com.example.loginfirebaseapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.loginfirebaseapp.AuthState
import com.example.loginfirebaseapp.AuthViewModel
import com.example.loginfirebaseapp.roomDB.AppDatabase
import com.example.loginfirebaseapp.roomDB.Comment
import com.example.loginfirebaseapp.roomDB.Post
import com.example.loginfirebaseapp.roomDB.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet(modifier: Modifier = Modifier, navController: NavController, db: AppDatabase) {


    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    var checked by remember { mutableStateOf(true) }
    var selected by remember { mutableStateOf(false) }
    var nameToSend by remember { mutableStateOf("") }
    var emailToSend by remember { mutableStateOf("") }
    var postTitle by remember { mutableStateOf("") }
    var postContent by remember { mutableStateOf("") }
    var postComment by remember { mutableStateOf("") }


    val scope = rememberCoroutineScope()



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // USER BIO DATA
        OutlinedTextField(
            value = nameToSend,
            onValueChange = { nameToSend = it },
            label = {
                Text(text = "Enter Your Name")
            },
            singleLine = true
        )
        OutlinedTextField(
            value = emailToSend,
            onValueChange = { emailToSend = it },
            label = {
                Text(text = "Enter Your Email")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = {
            navController.navigate("alertDialog/$nameToSend/$emailToSend")
        }) {
            Text(text = "Send this Data to another Screen")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // POST DATA
        OutlinedTextField(
            value = postTitle,
            onValueChange = { postTitle = it },
            label = {
                Text(text = "Enter Post Title")
            },
            singleLine = true
        )
        OutlinedTextField(
            value = postContent,
            onValueChange = { postContent = it },
            label = {
                Text(text = "Enter Post Content")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Post Comment
        OutlinedTextField(
            value = postComment,
            onValueChange = { postComment = it },
            label = {
                Text(text = "Post a Comment on this Post")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))
        //Save Data to Room Btn
        OutlinedButton(onClick = {

//                val userId: Int = (db.appDao().insertUser(User(name= nameToSend, email = emailToSend))).toString().toInt()
//                val postId: Int = (db.appDao().insertPost(Post(title = postTitle, content = postContent, authorId = userId))).toString().toInt()
//                db.appDao().insertComment(Comment(content = postComment, postId = postId))



        }) {
            Text(text = "Save this Data in Local Database")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { showBottomSheet = true }
        ) {
            Text("Display partial bottom sheet")
        }


        Button(
            onClick = {
                navController.navigate("alertDialog/$nameToSend/$emailToSend")
            }
        ) {
            Text("Go to Alert Dialog Screen")
        }




        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(24.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(onClick = {}) {
                        Text("Outlined")
                    }

                    Text(
                        "Swipe up to open sheet. \n Swipe down to dismiss.",
                        modifier = Modifier.padding(16.dp)
                    )

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray,
                        ),
                        modifier = Modifier.size(width = 240.dp, height = 100.dp)
                    ) {
                        Text(
                            text = "Filled",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier
                            .size(width = 240.dp, height = 100.dp)
                    ) {
                        Text(
                            text = "Elevated",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            "Minimal checkbox"
                        )
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                    }

                    Text(
                        if (checked) "Checkbox is checked" else "Checkbox is unchecked"
                    )

                    AssistChip(
                        onClick = { Log.d("Assist chip", "hello world") },
                        label = { Text("Assist chip") },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Localized description",
                                Modifier.size(AssistChipDefaults.IconSize)
                            )
                        }
                    )

                    FilterChip(
                        onClick = { selected = !selected },
                        label = {
                            Text("Filter chip")
                        },
                        selected = selected,
                        leadingIcon = if (selected) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )


                }
            }
        }
    }
}
fun addUser(userName:String, userEmail: String){

}

