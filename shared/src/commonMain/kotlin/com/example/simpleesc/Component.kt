package com.example.simpleesc

interface ComponentKey<C>

interface Component<C> {
    val key: ComponentKey<C>
}