package com.example.simpleesc.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleesc.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BouncingBallsViewModel : ViewModel() {
    private val bouncingBallsDriver = BouncingBalls(
        width = 800.0,
        height = 1000.0,
        timestamp = System.currentTimeMillis().toDouble(),
    )
    val drawingObjects: StateFlow<DrawingObjectsContainer> = bouncingBallsDriver.drawingObjects
    val testText: StateFlow<String> = bouncingBallsDriver.testText

    init {
        viewModelScope.launch {
            bouncingBallsDriver.startLoop()
        }
    }
}