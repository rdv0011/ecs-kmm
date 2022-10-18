package com.example.simpleesc

interface Generator<T> {
    fun generate(): T
}

object UUIDGenerator: Generator<String> {
    override fun generate() = PlatformUtils.randomUUID()
}

