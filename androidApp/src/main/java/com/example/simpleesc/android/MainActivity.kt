package com.example.simpleesc.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import com.example.simpleesc.DrawingObject
import androidx.activity.viewModels
import androidx.compose.material.Text

class MainActivity : ComponentActivity() {
    private val bouncingBallsViewModel by viewModels<BouncingBallsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                BouncingBalls(
                    bouncingBallsViewModel
                )
            }
        }
    }
}

@Composable
fun BouncingBalls(bouncingBallsViewModel: BouncingBallsViewModel) {
    val drawingObjects by bouncingBallsViewModel.drawingObjects.collectAsState()
    val timestamp by bouncingBallsViewModel.text.collectAsState()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Text(timestamp)
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawingObjects.forEach {
                        when (it) {
                            is DrawingObject.Circle ->
                                drawCircle(
                                    color = Color(it.color.red, it.color.green, it.color.blue),
                                    center = Offset(
                                        x = it.center.x.toFloat(),
                                        y = it.center.y.toFloat()
                                    ),
                                    radius = it.radius.toFloat(),
                                    style = Fill
                                )
                            else -> Unit
                        }

                    }
                }
            }
        }
    }
}
