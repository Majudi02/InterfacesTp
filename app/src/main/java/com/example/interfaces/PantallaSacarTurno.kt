package com.example.interfaces

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.interfaces.ui.theme.InterfacesTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit


class PantallaSacarTurno : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Screen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
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
                        .padding(start = 15.dp, end = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            SelectEspecialidad()
            Spacer(modifier = Modifier.height(10.dp))
            SelectDoctor()
            Spacer(modifier = Modifier.height(7.dp))
            Calendario()
            Spacer(modifier = Modifier.height(10.dp))
            horarios()

            Spacer(modifier = Modifier.height(100.dp))
            Button(
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.AzulApp)),
                onClick = {
                    /*  */
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text("Sacar Turno")
            }

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun horarios(){
        val context = LocalContext.current
        val specialties = arrayOf("13:00", "14:15", "14:45", "17:30")
        var expanded by remember { mutableStateOf(false) }
        var selectedSpecialty by remember { mutableStateOf("Seleccione un Horario") }

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
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
                        Text(text = "Horario")
                    },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
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


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Calendario() {
        var date by remember { mutableStateOf("") }
        var isDropdownMenuVisible by remember { mutableStateOf(false) }
        var isTextVisible by remember { mutableStateOf(false) }

        Column {
            Button(
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.AzulApp)),
                onClick = {
                    isDropdownMenuVisible = !isDropdownMenuVisible
                    isTextVisible = false // Ocultar el texto cuando se toca el botón
                },
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Seleccionar fecha")
            }

            if (isTextVisible) {
                Text(text = "Fecha seleccionada: $date", modifier = Modifier.padding(start = 24.dp))
            }

            DropdownMenu(
                expanded = isDropdownMenuVisible,
                onDismissRequest = {
                    isDropdownMenuVisible = false
                    isTextVisible = true
                }
            ) {
                calendario(date = date) {
                    date = it
                    isDropdownMenuVisible = false
                    isTextVisible = true
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun calendario(date: String, onDateSelected: (String) -> Unit) {
        Column {
            AndroidView(
                factory = { context ->
                    DatePicker(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                },
                update = {
                    it.setOnDateChangedListener { _, year, month, dayOfMonth ->
                        val formattedDate = "$dayOfMonth - ${month + 1} - $year"
                        onDateSelected(formattedDate)
                    }
                }
            )
        }
    }

    @Composable
    fun calendario() {
        var date by remember {
            mutableStateOf("")
        }
        Column {
            AndroidView(
                factory = { context ->
                    CalendarView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                },
                update = {
                    it.setOnDateChangeListener { calendarView, year, month, day ->
                        date = "$day - ${month + 1} - $year"
                    }
                }
            )
            Text(text = date)
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SelectDoctor() {
        val context = LocalContext.current
        val specialties =
            arrayOf("Juan Rodriguez", "María Fernández", "Valentina Ramírez", "Alejandro Torres")
        var expanded by remember { mutableStateOf(false) }
        var selectedSpecialty by remember { mutableStateOf("Seleccione un Medico") }

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
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
                        Text(text = "Medico")
                    },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
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
                        Text(text = "Especialidad")
                    },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()                )

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


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun DatePickerDialogSample() {
        var selectedDate by remember { mutableStateOf(LocalDate.now()) }
        val openDialog = remember { mutableStateOf(false) }

        Button(onClick = { openDialog.value = true }) {
            Text(text = "Show DatePicker")
        }

        if (openDialog.value) {
            val onDismissRequest = { openDialog.value = false }

            Dialog(onDismissRequest = onDismissRequest) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White) // Set the background color of the content
                ) {
                    val calendar = Calendar.getInstance()
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    val month = calendar.get(Calendar.MONTH)
                    val year = calendar.get(Calendar.YEAR)

                    AndroidView(factory = { context ->
                        CalendarView(context).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        }
                    },
                        update = { calendarView ->
                            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                                // Convertir a LocalDate
                                selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                            }
                        })

                    Button(
                        onClick = {
                            // Aquí puedes realizar acciones con la fecha seleccionada
                            // Por ejemplo, cerrar el diálogo y mostrar la fecha seleccionada en algún lugar
                            openDialog.value = false
                            // También puedes realizar otras acciones con la fecha seleccionada
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Seleccionar fecha")
                    }
                }
            }
        }

        Text(
            text = "Selected date is ${selectedDate}",
            modifier = Modifier.background(Color.White) // Ajusta el color de fondo del Text
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun PedirTurnoScreenPreview() {
        Screen()
    }

}