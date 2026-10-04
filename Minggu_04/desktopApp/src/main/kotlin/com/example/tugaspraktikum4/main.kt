package com.example.tugaspraktikum4

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TugasPraktikum4",
    ) {
        App()
    }
}