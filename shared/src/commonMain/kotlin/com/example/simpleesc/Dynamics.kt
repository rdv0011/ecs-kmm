package com.example.simpleesc

class Dynamics(
    val mass: Double,
    private val initialPosition: Vector2D<Double> = Vector2D.zeroDouble,
    private val initialVelocity: Vector2D<Double> = Vector2D.zeroDouble,
    private val initialAcceleration: Vector2D<Double> = Vector2D.zeroDouble,
): Component<Dynamics> {
    companion object : ComponentKey<Dynamics>
    override val key: ComponentKey<Dynamics> = Dynamics

    var position = initialPosition
    var velocity = initialVelocity
    var acceleration = initialAcceleration

    fun applyForce(force: Vector2D<Double>) {
        acceleration += force / mass
    }

    fun update(dt: Double) {
        velocity += acceleration * dt
        position += velocity * dt
    }
}