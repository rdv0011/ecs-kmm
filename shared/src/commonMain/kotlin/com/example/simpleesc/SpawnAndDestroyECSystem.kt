package com.example.simpleesc

import kotlin.random.Random

class SpawnAndDestroyECSystem<Id> (
    override val backend: Backend<Id>,
    val bounds: Rectangle<Double>,
    val spawnTotal: Int,
    val spawnVelocity: Pair<Double, Double>
) : AbstractECSystem<Id>() {
    override val keys: Set<ComponentKey<*>> = setOf(Dynamics)

    private var totalEntities: Int = 0
    private val entitiesToDestroy = mutableListOf<Id>()

    override fun beforeProcess() {
        super.beforeProcess()

        totalEntities = 0
        entitiesToDestroy.clear()
    }

    override fun process(entity: Id) {
        backend.get(entity, Dynamics).also { dynamics ->
            if (bounds.contains(dynamics.position)) {
                totalEntities += 1
            } else {
                entitiesToDestroy.add(entity)
            }
        }
    }

    override fun afterProcess() {
        super.afterProcess()

        entitiesToDestroy.forEach { id ->
            backend.destroy(id)
        }

        val entitiesToSpawn = spawnTotal - totalEntities
        (0 until entitiesToSpawn).forEach { _ ->
            backend.create().also { id ->
                val ltr = Random.nextBoolean()
                val initialVelocity = Vector2D(
                    Random.nextDouble(spawnVelocity.first, spawnVelocity.second) * (if (ltr) 1.0 else -1.0),
                    0.0
                )
                val dynamics = Dynamics(
                    mass = 1.0,
                    initialPosition = Vector2D(
                        if (ltr) bounds.origin.x else bounds.origin.x + bounds.width - 1,
                        Random.nextDouble(
                            bounds.origin.y,
                            bounds.origin.y + bounds.height - 1
                        )
                    ),
                    initialVelocity = initialVelocity,
                    initialAcceleration = Vector2D.zeroDouble
                )
                backend.set(id, dynamics)
                backend.set(id, Color.random())
            }
        }
    }
}