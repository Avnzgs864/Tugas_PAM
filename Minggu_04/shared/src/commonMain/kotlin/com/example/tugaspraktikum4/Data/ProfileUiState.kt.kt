package com.example.tugaspraktikum4.data

data class ProfileUiState(
    val name: String = "Refin Agus Saputra",
    val bio: String = "Mahasiswa yang sedang belajar Android dengan Jetpack Compose.",
    val isDarkMode: Boolean = false,
    val isEditing: Boolean = false
)