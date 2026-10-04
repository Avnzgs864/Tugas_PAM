package com.example.praktikum2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Praktikum2",
    ) {
        App()
    }
}