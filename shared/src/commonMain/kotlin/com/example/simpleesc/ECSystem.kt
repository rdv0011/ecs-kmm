package com.example.simpleesc

interface ECSystem<Id> {
    val keys: Set<ComponentKey<*>>
    val backend: Backend<Id>

    fun beforeProcess()
    fun process(entity: Id)
    fun afterProcess()
}

abstract class AbstractECSystem<Id>: ECSystem<Id> {
    override fun beforeProcess() {}
    override fun afterProcess() {}
}

class SystemRunner<Id>(
    private val backend: Backend<Id>,
    private vararg val systems: ECSystem<Id>
    ) {
    operator fun invoke() {
        systems.forEach { system ->
            system.beforeProcess()
            backend.entities().forEach { entity ->
                if (system.keys.all { key -> backend.has(entity, key) }) {
                    system.process(entity)
                }

            }
            system.afterProcess()
        }
    }
}