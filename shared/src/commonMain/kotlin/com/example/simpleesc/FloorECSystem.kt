package com.example.simpleesc

class FloorECSystem<Id>(
    override val backend: Backend<Id>,
    private val height: Double,
): AbstractECSystem<Id>() {
    override val keys: Set<ComponentKey<*>> = setOf(Dynamics)

    override fun process(entity: Id) {
        backend.get(entity, Dynamics).also { dynamics ->
            if (dynamics.position.y > height) {
                dynamics.position = Vector2D(
                    dynamics.position.x,
                    2 * height - dynamics.position.y
                )
                dynamics.velocity = Vector2D(
                    dynamics.velocity.x,
                    -dynamics.velocity.y
                )
            }

        }
    }
}