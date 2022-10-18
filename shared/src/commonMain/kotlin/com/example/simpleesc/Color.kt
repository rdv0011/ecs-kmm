package com.example.simpleesc

import kotlin.random.Random

data class Color(
    val red: Int,
    val green: Int,
    val blue: Int
): Component<Color> {
    companion object : ComponentKey<Color> {
        fun random(): Color = Color(
            red = Random.nextInt(0, 256),
            green = Random.nextInt(0, 256),
            blue = Random.nextInt(0, 256),
        )
    }

    override val key: ComponentKey<Color> = Color

    override fun toString(): String =
        "#" + listOf(red, green, blue).joinToString(separator = "") {
            it.coerceIn(0, 255).toString(16)
        }
}