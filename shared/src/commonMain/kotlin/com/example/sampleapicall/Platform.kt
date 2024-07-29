package com.example.sampleapicall

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform