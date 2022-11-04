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
    val timestamp by bouncingBallsViewModel.testText.collectAsState()

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
                    val iterator = drawingObjects.iterator()
                    while(iterator.hasNext()){
                        when (val item = iterator.next()) {
                            is DrawingObject.Circle ->
                                drawCircle(
                                    color = Color(item.color.red, item.color.green, item.color.blue),
                                    center = Offset(
                                        x = item.center.x.toFloat(),
                                        y = item.center.y.toFloat()
                                    ),
                                    radius = item.radius.toFloat(),
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
