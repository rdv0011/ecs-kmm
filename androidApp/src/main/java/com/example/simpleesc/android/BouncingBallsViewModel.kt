package com.example.simpleesc.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleesc.BouncingBalls
import com.example.simpleesc.Color
import com.example.simpleesc.DrawingObject
import com.example.simpleesc.Vector2D
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class BouncingBallsViewModel : ViewModel() {
    private val bouncingBallsDriver = BouncingBalls(
        width = 800.0,
        height = 1000.0,
        timestamp = System.currentTimeMillis().toDouble(),
    )
    val drawingObjects: StateFlow<Array<DrawingObject>> = bouncingBallsDriver.drawingObjects

    init {
        viewModelScope.launch {
            bouncingBallsDriver.startLoop()
        }
    }
}