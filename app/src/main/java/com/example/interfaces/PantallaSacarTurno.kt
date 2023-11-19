package com.example.interfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfaces.ui.theme.InterfacesTheme

class PantallaSacarTurno : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfacesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Screen()


                }
            }

        }
    }

    @Composable
    fun Screen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth()
                    //        .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.AzulApp))
            ) {
                Text(
                    text = "Pedir Turno",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            SelectEspecialidad()
            Spacer(modifier = Modifier.height(20.dp))
            SelectDoctor()
        }
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SelectDoctor() {
        val context = LocalContext.current
        val specialties = arrayOf("Juan Rodriguez","María Fernández","Valentina Ramírez","Alejandro Torres")
        var expanded by remember { mutableStateOf(false) }
        var selectedSpecialty by remember { mutableStateOf("Seleccione un Medico") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
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
                    label = {
                        Text(text = "Medico") // Ajusta el texto del label según tus necesidades
                    },
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


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SelectEspecialidad() {
        val context = LocalContext.current
        val specialties = arrayOf("General")
        var expanded by remember { mutableStateOf(false) }
        var selectedSpecialty by remember { mutableStateOf("Seleccione una Especialidad") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
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
                    label = {
                        Text(text = "Especialidad") // Ajusta el texto del label según tus necesidades
                    },
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

    /*
      @OptIn(ExperimentalMaterial3Api::class)
      @Composable
      fun Select() {
          val context = LocalContext.current
          val specialties = arrayOf("Perro", "Gato")
          var expanded by remember { mutableStateOf(false) }
          var selectedSpecialty by remember { mutableStateOf("") }

          Box(
              modifier = Modifier.fillMaxWidth()
                  .padding(32.dp)
          ) {
              ExposedDropdownMenuBox(
                  expanded = expanded,
                  onExpandedChange = {
                      expanded = !expanded
                  }
              ) {

                  Icon(
                      imageVector = Icons.Default.ArrowDropDown,
                      contentDescription = null,
                      modifier = Modifier
                          .size(24.dp)
                          .align(Alignment.CenterEnd).clickable { expanded = !expanded }, // O utiliza pointerInput ,
                  )
                  /*
                  TextField(
                      value = selectedSpecialty,
                      onValueChange = {},
                      readOnly = true,
                      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                      modifier = Modifier.menuAnchor() // Este modificador hará que las opciones aparezcan hacia abajo
                  )

                   */

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
      } */


}