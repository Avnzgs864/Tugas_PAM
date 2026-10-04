package com.example.tugaspraktikum4.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditProfileScreen(
    initialName: String,
    initialBio: String,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var bio by remember { mutableStateOf(initialBio) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Edit Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        ProfileTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nama"
        )
        Spacer(Modifier.height(16.dp))
        ProfileTextField(
            value = bio,
            onValueChange = { bio = it },
            label = "Bio",
            singleLine = false
        )

        Spacer(Modifier.height(24.dp))
        Row {
            OutlinedButton(onClick = onCancel) { Text("Batal") }
            Spacer(Modifier.width(12.dp))
            Button(
                onClick = { onSave(name, bio) },
                enabled = name.isNotBlank()
            ) { Text("Simpan") }
        }
    }
}

@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        modifier = Modifier.fillMaxWidth()
    )
}