package com.example.simpleesc

import platform.Foundation.NSDate
import platform.Foundation.NSUUID
import platform.Foundation.timeIntervalSince1970

actual class PlatformUtils {
    actual companion object {
        actual fun randomUUID(): String = NSUUID().UUIDString
        actual fun currentTimeMillis(): Double = NSDate().timeIntervalSince1970 * 1000
    }
}