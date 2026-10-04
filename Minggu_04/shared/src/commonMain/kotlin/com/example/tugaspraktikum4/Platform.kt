package com.example.tugaspraktikum4

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
