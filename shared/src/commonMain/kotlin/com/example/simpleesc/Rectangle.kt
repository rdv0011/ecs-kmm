package com.example.simpleesc

data class Rectangle<T>(val origin: Vector2D<T>, val width: T, val height: T)

fun Rectangle<Double>.contains(point: Vector2D<Double>): Boolean =
    point.x > origin.x && point.x < origin.x + width &&
            point.y > origin.y && point.y < origin.y + height
