package com.example.simpleesc

sealed class DrawingObject {
    data class Circle(
        val center: Vector2D<Double>,
        val radius: Double,
        val color: Color,
        ): DrawingObject()
}

class DrawingECSystem<Id>(
    override val backend: Backend<Id>,
    val radius: Double,
    val drawingContext: (DrawingObject) -> Unit,
): AbstractECSystem<Id>() {
    override val keys: Set<ComponentKey<*>> = setOf(Color, Dynamics)

    override fun process(entity: Id) {
        backend.get(entity, Dynamics, Color) { dynamics, color ->
            drawingContext(DrawingObject.Circle(dynamics.position, radius, color))
        }
    }
}