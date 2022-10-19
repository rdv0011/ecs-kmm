package com.example.simpleesc

expect class PlatformUtils {
    companion object {
        fun randomUUID(): String
        fun currentTimeMillis(): Double
    }
}