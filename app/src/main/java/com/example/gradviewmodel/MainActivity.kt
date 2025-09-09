package com.example.gradviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gradviewmodel.ui.theme.GradViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GradViewModelTheme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    val gradViewModel: GradViewModel = viewModel()
    GradScreen(innTemp= gradViewModel.inputTemperature.value,
        resultat= gradViewModel.outputTemperature.value,
        tilGrader = { gradViewModel.convertToCelsius(it) },
        tilFahrenheit = { gradViewModel.convertToFahrenheit(it)},
        inputSkift = {gradViewModel.onInputTemperatureChange(it)}
    )
}

@Composable
fun GradScreen(
    innTemp: String,
    resultat: String,
    tilGrader: (String) -> Unit,
    tilFahrenheit: (String) -> Unit,
    inputSkift: (String) -> Unit
){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Temperaturkonverterer", fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(16.dp))
            TextField(
                modifier = Modifier.padding(16.dp),
                label = { Text("Skriv inn temperatur:") },
                value = innTemp,
                onValueChange = {inputSkift(it)}
            )

            Spacer(modifier = Modifier.padding(16.dp))
            Text("Resultatet er :$resultat")
            Spacer(modifier = Modifier.padding(16.dp))
            Row(modifier = Modifier) {
                Button(onClick = { tilGrader(innTemp) })
                {
                    Text("Til Grader")
                }
                Button(onClick = { tilFahrenheit(innTemp) })
                {
                    Text("Til Fahrenheit")
                }

            }
        }
    }
}

