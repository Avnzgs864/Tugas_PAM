package com.example.praktikum2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform