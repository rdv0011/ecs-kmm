package com.example.simpleesc

interface Backend<Id> {
    fun create(id: Id? = null): Id
    fun exists(id: Id): Boolean
    fun destroy(id: Id): Id
    fun entities(): Sequence<Id>

    fun <C : Component<C>> set(id: Id, component: C): C?
    fun has(id: Id, key: ComponentKey<*>): Boolean
    fun <C> get(id: Id, key: ComponentKey<C>): C
    fun <C> unset(id: Id, key: ComponentKey<C>): C
}

fun <Id, C1, C2> Backend<Id>.get(id: Id, k1: ComponentKey<C1>, k2: ComponentKey<C2>,
                                 block: (C1, C2) -> Unit) =
    block(get(id, k1), get(id, k2))