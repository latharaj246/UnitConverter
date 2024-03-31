package com.example.unitconverter

import android.graphics.Paint.Style
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme. background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpended by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    var convertionFactor = remember { mutableStateOf(0.01)}
    val oConvertionFactor = remember { mutableStateOf(0.01)}

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Red
    )


fun convertUnit(){
    val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
    val result = (inputValueDouble * convertionFactor.value * 100.0 / oConvertionFactor.value).roundToInt() / 100.0
    outputValue = result.toString()
}


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
     Text("Unit Converter", style =customTextStyle )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={inputValue = it}, label = { Text(text = "Enter Value")} )
        Row {
            val context = LocalContext.current
        Button(onClick = { Toast
            .makeText(context,
            "Thanks for clicking",
                Toast.LENGTH_LONG).show() }) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Click Me")
            Spacer(modifier = Modifier.height(16.dp))
        }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = {  iExpended = true }) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = inputUnit)
                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpended, onDismissRequest = { iExpended = false }) {
                    DropdownMenuItem(text = {Text("Meter")}, onClick = {
                        iExpended = false
                        inputValue = "Meter"
                        convertionFactor.value = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = {Text("Centimeter")}, onClick = {
                        iExpended = false
                        inputValue = "Centimeter"
                        convertionFactor.value = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = {Text("Feat")}, onClick = { oExpanded = false
                        inputValue = "Feat"
                        convertionFactor.value = 0.3048
                        convertUnit() })
                    DropdownMenuItem(text = {Text("Milimeter")}, onClick = {
                        inputValue = "Milimeter"
                        convertionFactor.value = 0.001
                        convertUnit() })
                    }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                //Input Button
                Button(onClick = { oExpanded = true }) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = outputUnit)
                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = {Text("Meter")}, onClick = {
                        oExpanded = false
                        outputUnit = "Meter"
                        oConvertionFactor.value = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = {Text("Centimeter")}, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeter"
                        oConvertionFactor.value = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = {Text("Feat")}, onClick = {
                        oExpanded = false
                        outputUnit = "Feat"
                        oConvertionFactor.value = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = {Text("Milimeter")}, onClick = {
                        oExpanded = false
                        outputUnit = "Milimeter"
                        oConvertionFactor.value = 0.001
                        convertUnit()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        //Result Text
        Text(text = "Result: $outputValue $outputUnit",
              style = MaterialTheme.typography.headlineMedium)
    }
}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}