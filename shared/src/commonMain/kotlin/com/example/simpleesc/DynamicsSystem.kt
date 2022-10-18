package com.example.simpleesc

class DynamicsSystem<Id>(
    override val backend: Backend<Id>
): AbstractECSystem<Id>() {
    override val keys: Set<ComponentKey<*>> = setOf(Dynamics)

    private var dt = 0.0

    fun setElapsedTime(dt: Double) {
        this.dt = dt
    }

    override fun process(entity: Id) {
        backend.get(entity, Dynamics).also { dynamics ->
            dynamics.update(dt)
        }
    }
}