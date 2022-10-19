package com.example.simpleesc

import java.util.*

actual class PlatformUtils {
    actual companion object {
        actual fun randomUUID(): String = UUID.randomUUID().toString()
        actual fun currentTimeMillis(): Double = System.currentTimeMillis().toDouble()
    }
}