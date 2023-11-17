package com.example.interfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.interfaces.ui.theme.InterfacesTheme

class PantallaSelecionarMascota: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfacesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = "hola")
                    Select()

                }
            }

        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Select() {
        val context = LocalContext.current
        val specialties = arrayOf("General", "Dermatologo", "Cardiologo")
        var expanded by remember { mutableStateOf(false) }
        var selectedSpecialty by remember { mutableStateOf("Seleccione") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = selectedSpecialty,
                    onValueChange = {},
                    label={ Text(text = "Espacialidad")},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor() // Este modificador hará que las opciones aparezcan hacia abajo
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    specialties.forEach { specialty ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = specialty,
                                    //   color = getColorForSpecialtyText(specialty)
                                )
                            },
                            onClick = {
                                selectedSpecialty = specialty
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}