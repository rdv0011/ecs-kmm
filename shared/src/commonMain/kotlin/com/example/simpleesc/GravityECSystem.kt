package com.example.simpleesc

class GravityECSystem<Id>(
    override val backend: Backend<Id>,
    private val g: Double,
) : AbstractECSystem<Id>() {
    override val keys = setOf(Dynamics)

    override fun process(entity: Id) {
        backend.get(entity, Dynamics).also { dynamics ->
            dynamics.acceleration = Vector2D.zeroDouble
            dynamics.applyForce(Vector2D(0.0, g * dynamics.mass))
        }
    }
}