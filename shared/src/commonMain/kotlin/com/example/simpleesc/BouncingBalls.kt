package com.example.simpleesc

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

typealias DrawingObjectsContainer = List<DrawingObject>

class BouncingBalls(
    val width: Double,
    val height: Double,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    timestamp: Double,
): CoroutineScope by CoroutineScope(defaultDispatcher.limitedParallelism(1)) {
    private val _drawingObjects: MutableStateFlow<DrawingObjectsContainer> = MutableStateFlow(emptyList())
    private var drawingObjectsBaking: DrawingObjectsContainer = emptyList()
    val drawingObjects: StateFlow<DrawingObjectsContainer> = _drawingObjects

    private val backend = HashMapBackend(UUIDGenerator)
    private val dynamicsSystem = DynamicsSystem(backend)
    private val gravitySystem = GravityECSystem(backend, 300.0)
    private val floorSystem = FloorECSystem(backend, height)
    private val spawnerAndDestroySystem = SpawnAndDestroyECSystem(
        backend,
        bounds = Rectangle(Vector2D(0.0, 0.0), width, height),
        spawnTotal = 25,
        spawnVelocity = 15.0 to 50.0
    )
    private val drawingSystem = DrawingECSystem(
        backend,
        radius = 25.0
    ) { objectToDraw ->
        drawingObjectsBaking += objectToDraw
    }
    private val runner = SystemRunner(
        backend,
        spawnerAndDestroySystem,
        gravitySystem,
        dynamicsSystem,
        floorSystem,
        drawingSystem
    )
    private var lastTimeStamp = timestamp
    private var dt = 0.0

    fun startLoop() {
        launch {
            while(isActive) {
                drawingObjectsBaking = emptyList()
                val timestamp = PlatformUtils.currentTimeMillis()
                dt = (timestamp - lastTimeStamp)
                lastTimeStamp = timestamp
                dynamicsSystem.setElapsedTime(dt / 1000.0)
                runner.invoke()
                _drawingObjects.update { drawingObjectsBaking }
                delay(33)
            }
        }
    }
}