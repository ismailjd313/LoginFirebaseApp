package com.example.loginfirebaseapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginfirebaseapp.R

@Composable
fun NamesListPage(modifier: Modifier = Modifier) {
    var names = mutableListOf("Muhammad","ismail","Yousaf", "Khan")
    var newName by remember { mutableStateOf("") }




    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            names.forEachIndexed { index, name ->
                MyListText(text = "$index :  $name")
            }
        }

        Text(text = names.size.toString())

        OutlinedTextField(
            modifier = Modifier.width(200.dp),
            value = newName,
            onValueChange = { newName = it },
            label = { Text("Enter New Name") },
            shape = RoundedCornerShape(
                if (newName.length > 7) 20.dp else 0.dp
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.Face, ""
                )
            },
            trailingIcon = {
                if (newName.length >= 8) {
                    Icon(
                        Icons.Filled.Check, ""
                    )
                } else {
                    Icon(
                        Icons.Filled.Info, "",
                        modifier = Modifier.clickable(onClick = {
                            newName = "Info Clicked"
                        })
                    )
                }
            },
            placeholder = {
                Text("Enter New Name Here")
            },
            maxLines = 8,
            minLines = 4
        )
        OutlinedButton(onClick = {
            names.add(newName)
            newName = ""
        } , shape = RoundedCornerShape(3.dp)
        ) {
            Text("Add into List")
        }



        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), "")
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), "")


    }
}



@Composable
fun MyListText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 32.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Gray
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MyNamesListPagePreview() {
    NamesListPage()

}