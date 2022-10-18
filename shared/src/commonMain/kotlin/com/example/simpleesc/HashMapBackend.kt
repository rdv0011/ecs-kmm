package com.example.simpleesc

class HashMapBackend<Id>(
    private val idGenerator: Generator<Id>,
): Backend<Id> {
    private val components = mutableMapOf<Id, MutableMap<ComponentKey<*>, Component<*>>>()

    override fun create(id: Id?): Id =
        (id ?: idGenerator.generate()).also {
            components[it] = mutableMapOf()
        }

    override fun exists(id: Id): Boolean =
        components.containsKey(id)

    override fun destroy(id: Id): Id =
        id.also {
            components.remove(it)
        }

    override fun entities(): Sequence<Id> =
        components.keys.asSequence()

    @Suppress("UNCHECKED_CAST")
    override fun <C : Component<C>> set(id: Id, component: C): C? =
        components[id]?.put(component.key, component) as? C

    override fun has(id: Id, key: ComponentKey<*>): Boolean =
        components[id]?.containsKey(key) == true

    @Suppress("UNCHECKED_CAST")
    override fun <C> get(id: Id, key: ComponentKey<C>): C {
        val component = components[id]?.get(key) as? C
        require(component!= null)
        return component
    }

    @Suppress("UNCHECKED_CAST")
    override fun<C> unset(id: Id, key: ComponentKey<C>): C {
        val component = components[id]?.remove(key) as? C
        require(component!= null)
        return component
    }

}