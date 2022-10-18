package com.example.simpleesc

import platform.Foundation.NSUUID

actual class PlatformUtils {
    actual companion object {
        actual fun randomUUID(): String = NSUUID().UUIDString
    }
}