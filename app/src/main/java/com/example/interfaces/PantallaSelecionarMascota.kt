package com.example.interfaces

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfaces.ui.theme.InterfacesTheme

class PantallaSelecionarMascota : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfacesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Select()
                    SelecionarMascota()

                }
            }

        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SelecionarMascota() {
        Scaffold(modifier = Modifier.background(color=colorResource(id = R.color.AzulApp)),
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "Seleccione su Mascota", fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        //   fontFamily = FontFamily.Serif,
                        fontFamily = FontFamily.Monospace,
                        color=colorResource(id =R.color.AzulApp )
                    )
                })
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(10.dp))

            /*    Row {
                    Image(
                        painterResource(id = R.drawable.perfil),
                        contentDescription = "",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(start = 15.dp)
                    )
                    Text(
                        text = "Bienvenido Rigoberto",
                        modifier = Modifier.padding(start = 5.dp, top = 20.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

             */

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Seleccione su Mascota",
                    modifier = Modifier
                        .fillMaxWidth(),
                    //   .padding(start = 45.dp, top = 23.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    //   fontFamily = FontFamily.Serif,
                    fontFamily = FontFamily.Monospace,
                    color = colorResource(id = R.color.AzulApp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            startActivity(
                                Intent(
                                    this@PantallaSelecionarMascota,
                                    PantallaPrincipal::class.java
                                )
                            )
                        }) {
                    Image(
                        painterResource(id = R.drawable.perro_perfil),
                        contentDescription = "",
                        modifier = Modifier
                            .size(180.dp)
                            .padding(start = 10.dp)

                    )
                    Column {
                        Text(
                            text = "Carlitos",
                            modifier = Modifier.padding(start = 15.dp, top = 25.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Ultima Visita: 25/10/2023",
                            modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                            fontSize = 13.5.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            startActivity(
                                Intent(
                                    this@PantallaSelecionarMascota,
                                    PantallaPrincipal::class.java
                                )
                            )
                        }) {
                    Image(
                        painterResource(id = R.drawable.gato_perfil),
                        contentDescription = "",
                        modifier = Modifier
                            .size(180.dp)
                            .padding(start = 10.dp)

                    )
                    Column {
                        Text(
                            text = "Nombre",
                            modifier = Modifier.padding(start = 15.dp, top = 25.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Ultima Visita: 10/6/2023",
                            modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                            fontSize = 13.5.sp
                        )
                    }
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
                    label = { Text(text = "Espacialidad") },
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
    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun SeleccionarMascotaScreenPreview() {
        SelecionarMascota()
    }
}