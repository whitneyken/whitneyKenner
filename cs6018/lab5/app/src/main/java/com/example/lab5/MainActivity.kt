package com.example.lab5

import android.os.Bundle
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab5.ui.theme.Lab5Theme
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow



class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        val gravityFlow: Flow<FloatArray> = movementDetector(gravitySensor, sensorManager)

        setContent {
            Lab5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        var xVal by remember { mutableFloatStateOf(0f) }
                        var yVal by remember { mutableFloatStateOf(0f) }

                        MovingMarble(xVal, yVal)

                        LaunchedEffect(key1 = gravityFlow) {
                            gravityFlow.collect { movementReading ->
                                xVal = movementReading[0]
                                yVal = movementReading[1]

                            }
                        }

                    }
                }
            }
        }
    }


    @Composable
    fun MovingMarble(x: Float?, y: Float?, modifier: Modifier = Modifier) {

        var xOffset by remember { mutableFloatStateOf(165f) }
        var yOffset by remember { mutableFloatStateOf(375f) }

        if (y != null) {
            Log.e("WTF", y.toString())
            if (y > 0) {
                yOffset += 3
            } else if(y < 0){
                yOffset -= 3
            }
            if (x != null) {
                if (x > 0) {
                    xOffset -= 3
                } else if (x < 0) {
                    xOffset += 3
                }
            }
        }

        BoxWithConstraints {
            if (xOffset < 1) {
                xOffset = 0f
            }
            if (xOffset > 330) {
                xOffset = 330f
            }
            if (yOffset < 0) {
                yOffset = 0f
            }
            if (yOffset > 760) {
                yOffset = 760f
            }
            Log.e("offsetVals", maxHeight.toString())
            Box(
                modifier = modifier
                    .offset( //place it
                        xOffset.dp,
                        yOffset.dp

                    )

                    .size(80.dp)
                    .clip(CircleShape) //only draw in a circle
                    .drawBehind {
                        drawRect(Color.Magenta)
                    },
                contentAlignment = Alignment.Center //put the text in the middle of the circle
            ) {
                Text("")

            }
        }

    }

    private fun movementDetector(gravity: Sensor, sensorManager: SensorManager): Flow<FloatArray> {
        return channelFlow {
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (event !== null) {
                        Log.e("Sensor event!", event.values[1].toString())
                        var success = channel.trySend(event.values).isSuccess
                        Log.e("success?", success.toString())
                    }
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                }
            }
            sensorManager.registerListener(listener, gravity, SensorManager.SENSOR_DELAY_GAME)

            awaitClose {
                sensorManager.unregisterListener(listener)
            }
        }
    }
}