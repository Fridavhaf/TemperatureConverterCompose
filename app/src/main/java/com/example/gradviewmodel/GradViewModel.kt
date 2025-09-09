package com.example.gradviewmodel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GradViewModel: ViewModel() {
    private val _inputTemperature = mutableStateOf("")
    val inputTemperature: State<String> = _inputTemperature
    private val _outputTemperature = mutableStateOf("")
    val outputTemperature: State<String> = _outputTemperature


    fun onInputTemperatureChange(newValue: String) {
        _inputTemperature.value = newValue
        _outputTemperature.value =""
    }
    fun convertToFahrenheit(_inputTemperature: String) {
        val celsius = _inputTemperature.toDoubleOrNull()
        if (celsius != null) {
            _outputTemperature.value = ((celsius * 9.0 / 5.0) + 32).format(2)+ " °F"
        }
    }

    fun convertToCelsius(_inputTemperature: String) {
        val fahrenheit = _inputTemperature.toDoubleOrNull()
        if (fahrenheit != null) {
            _outputTemperature.value = (((fahrenheit - 32) * 5.0 / 9.0)).format(2) +
                    " °C"
        }
    }
    private fun Double.format(decimalPlaces: Int): String {
        return "%.${decimalPlaces}f".format(this)
    }


}

