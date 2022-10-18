package com.example.simpleesc.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleesc.BouncingBalls
import com.example.simpleesc.DrawingObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class BouncingBallsViewModel : ViewModel() {
    private val bouncingBallsDriver = BouncingBalls(
        width = 500.0,
        height = 1000.0,
        timestamp = System.currentTimeMillis().toDouble(),
    )
    val drawingObjects: StateFlow<List<DrawingObject>> = bouncingBallsDriver.drawingObjects
    val text = MutableStateFlow("")

    init {
        viewModelScope.launch {
            while (isActive) {
                bouncingBallsDriver.loop(System.currentTimeMillis().toDouble())
                delay(100)
                text.update { System.currentTimeMillis().toDouble().toString() }
            }
        }
    }
}