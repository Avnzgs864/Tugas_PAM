package com.example.tugaspraktikum4.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.tugaspraktikum4.viewmodel.ProfileViewModel

@Composable
fun ProfileApp(viewModel: ProfileViewModel = remember { ProfileViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme(
        colorScheme = if (uiState.isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            if (uiState.isEditing) {
                EditProfileScreen(
                    initialName = uiState.name,
                    initialBio = uiState.bio,
                    onSave = viewModel::saveProfile,
                    onCancel = viewModel::cancelEditing
                )
            } else {
                ProfileScreen(
                    uiState = uiState,
                    onEditClick = viewModel::startEditing,
                    onDarkModeChange = viewModel::setDarkMode
                )
            }
        }
    }
}