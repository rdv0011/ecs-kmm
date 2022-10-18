package com.example.simpleesc

data class Vector2D<T>(val x: T, val y: T) {
    companion object {
        val zeroDouble
            get() = Vector2D(0.0, 0.0)
    }
}

operator fun Vector2D<Double>.plus(increment: Vector2D<Double>): Vector2D<Double> =
    Vector2D(x + increment.x, y + increment.y)

operator fun Vector2D<Double>.times(by: Double): Vector2D<Double> =
    Vector2D(x * by, y * by)

operator fun Vector2D<Double>.div(by: Double): Vector2D<Double> =
    Vector2D(x / by, y / by)