package com.example.interfaces

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.interfaces.ui.theme.InterfacesTheme
import java.time.format.TextStyle

class PantallaPrincipal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfacesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PantallaPrincipalScreen()
                }
            }
        }
    }

    @Composable
    fun PantallaPrincipalScreen() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Carlitos",
                modifier = Modifier.align(CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painterResource(id = R.drawable.perro_perfil),
                contentDescription = "",
                modifier = Modifier
                    .size(160.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )


            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(vertical = 10.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        startActivity(
                            Intent(
                                this@PantallaPrincipal, PantallaSacarTurno::class.java
                            )
                        )
                    }) {
                    Text(
                        text = "Pedir Turno",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onPrimary



                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(90.dp)
                            .padding(start = 5.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        Text(
                            text = "Laboratorios",
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(90.dp)
                            .background(
                                 color = MaterialTheme.colorScheme.primaryContainer,shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        Text(
                            text = "Vacunas",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(start = 5.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer

                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .height(90.dp)
                            .background(
                                 color = MaterialTheme.colorScheme.primaryContainer,shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        Text(
                            text = "Historia Clínica",
                            modifier = Modifier.padding(start=25.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                Spacer(modifier = Modifier.height(45.dp))


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(20.dp))
                ) {
                    Column(modifier = Modifier.align(Alignment.TopStart).padding(12.dp)) {
                        Text(
                            text = "Proximos Turnos",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row {
                            Image(
                                painterResource(id = R.drawable.calendario),
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "29/11/2023",
                                modifier = Modifier.padding(start = 20.dp, top = 7.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row {
                            Image(
                                painterResource(id = R.drawable.vacuna),
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "3/12/2023",
                                modifier = Modifier.padding(start = 20.dp, top = 7.dp)
                            )
                        }
                    }
                }

            }
        }


    }

    @Preview(showSystemUi = true, showBackground = true, device = "id:pixel_3a",
       )
    @Composable
    fun PantallaPrincipalScreenPreview() {
        PantallaPrincipalScreen()
    }
}
