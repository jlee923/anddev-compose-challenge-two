/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(
                    mainViewModel.startTime,
                    mainViewModel.time,
                    mainViewModel::updateTime,
                    mainViewModel::startClock,
                    mainViewModel::stopClock
                )
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(
    startTime: String,
    time: String,
    updateTime: (String) -> Unit,
    startClock: () -> Unit,
    stopClock: () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Countdown Timer",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
            )
            OutlinedTextField(
                value = startTime,
                onValueChange = { updateTime(it) },
                label = { Text("Seconds to Count Down") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            )

            Text(
                text = "Count: $time",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
            )

            CountDownButtons(startClock, stopClock)
        }
    }
}

@Composable
fun CountDownButtons(
    startClock: () -> Unit,
    stopClock: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { startClock() },
            Modifier.padding(16.dp)
        ) {
            Text(
                text = "Start",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
        }

        Button(
            onClick = { stopClock() },
            Modifier.padding(16.dp)
        ) {
            Text(
                text = "Stop",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp("120", "120", {}, {}, {})
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp("120", "120", {}, {}, {})
    }
}
