package com.example.loginfirebaseapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loginfirebaseapp.model.Category
import com.example.loginfirebaseapp.ui.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(viewModel: CategoryViewModel, onCategoryClick: (Int) -> Unit) {
    val categories by viewModel.allCategories.collectAsState(initial = emptyList())
    var categoryName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            label = { Text("Category Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (categoryName.isNotEmpty()) {
                    viewModel.insert(Category(name = categoryName))
                    categoryName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Category")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(categories) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = category.name,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = { onCategoryClick(category.categoryId) }) {
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Open Tasks")
                        }

                        IconButton(onClick = { viewModel.delete(category) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}