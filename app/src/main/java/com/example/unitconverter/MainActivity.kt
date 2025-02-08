package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var OutputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var OExpand by remember { mutableStateOf(false) }
    val conversionFactor =  remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble * conversionFactor.value * 100/ oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit() },
            label = {Text(text = "Enter value")}
            )
        Spacer(modifier = Modifier.height(16.dp))
//  Input box
        Row {
            Box {
                Button(onClick = { iExpand = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
//  Input Dropdown
                    DropdownMenu(expanded = iExpand , onDismissRequest = { iExpand = false }) {
                        DropdownMenuItem(
                            text = {Text(text = "Meters")},
                            onClick = {
                                iExpand = false
                                inputUnit = "Meters"
                                conversionFactor.value = 1.00
                            })
                        DropdownMenuItem(
                            text = {Text(text = "Centimeters")},
                            onClick = {
                                iExpand = false
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                            })
                        DropdownMenuItem(
                            text = {Text(text = "Millimeters")},
                            onClick = {
                                iExpand = false
                                inputUnit = "Meters"
                                conversionFactor.value = 0.001
                            })
                        DropdownMenuItem(
                            text = {Text(text = "Feet")},
                            onClick = {
                                iExpand = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.3048
                            })
                    }
                }
            Spacer(modifier = Modifier.width(16.dp))

//  Output Box
            Box {
                Button(onClick = { OExpand = true }) {
                    Text(text = OutputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                    DropdownMenu(expanded = OExpand , onDismissRequest = { OExpand = false }) {
//  Output Dropdown
                        DropdownMenuItem(
                            text = {Text(text = "Meters")},
                            onClick = {
                                OExpand = false
                                OutputUnit = "Meters"
                                oConversionFactor.value = 1.00
                            })
                        DropdownMenuItem(
                            text = {Text(text = "Centimeters")},
                            onClick = {
                                OExpand = false
                                OutputUnit = "Centimeters"
                                oConversionFactor.value = 0.01
                            })
                        DropdownMenuItem(
                            text = {Text(text = "Millimeters")},
                            onClick = {
                                OExpand = false
                                OutputUnit = "Millimeters"
                                oConversionFactor.value = 0.001
                            })
                        DropdownMenuItem(
                            text = {Text(text = "Feet")},
                            onClick = {
                                OExpand = false
                                OutputUnit = "Feet"
                                oConversionFactor.value = 0.3048
                            })
                    }
                }
            }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $OutputUnit", style = MaterialTheme.typography.headlineMedium)
        }
    }

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
        UnitConverter()
}