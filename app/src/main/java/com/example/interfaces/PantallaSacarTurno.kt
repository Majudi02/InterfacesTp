package com.example.interfaces

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import java.time.LocalDate
import java.util.Calendar


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

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Screen() {
        var showDialog by remember {
            mutableStateOf(false)
        }

        var horaDialog by remember { mutableStateOf("") }
        var especialodadDialog by remember { mutableStateOf("") }
        var doctorDialog by remember { mutableStateOf("") }
        var diaDialog by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.AzulApp))

            ) {
                Text(
                    text = "Pedir Turno",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 15.dp, end = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            val context23 = LocalContext.current
            val listaEspecialidades = arrayOf("General")
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listaEspecialidades.forEach { specialty ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = specialty,
                                    )
                                },
                                onClick = {
                                    selectedSpecialty = specialty
                                    especialodadDialog=selectedSpecialty
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
            //********************************************************************************************************************************************
            Spacer(modifier = Modifier.height(10.dp))
            //********************************************************************************************************************************************
            val context = LocalContext.current
            val listaDoctores =
                arrayOf("Juan Rodriguez", "María Fernández", "Valentina Ramírez", "Alejandro Torres")
            var expandedDoctores by remember { mutableStateOf(false) }
            var doctorSeleccionado by remember { mutableStateOf("Seleccione un Medico") }

            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                ExposedDropdownMenuBox(
                    expanded = expandedDoctores,
                    onExpandedChange = {
                        expandedDoctores = !expandedDoctores
                    }
                ) {
                    TextField(
                        value = doctorSeleccionado,
                        onValueChange = {},
                        label = {
                            Text(text = "Medico")
                        },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDoctores) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedDoctores,
                        onDismissRequest = { expandedDoctores = false }
                    ) {
                        listaDoctores.forEach { specialty ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = specialty,
                                    )
                                },
                                onClick = {
                                    doctorSeleccionado = specialty
                                    doctorDialog=doctorSeleccionado
                                    expandedDoctores = false
                                }
                            )
                        }
                    }
                }
            }
            //********************************************************************************************************************************************
            Spacer(modifier = Modifier.height(7.dp))
            //*******************************************************************************************************************************************
            var date by remember { mutableStateOf("") }
            var isDropdownMenuVisibleCalen by remember { mutableStateOf(false) }
            var isTextVisible by remember { mutableStateOf(false) }

            Column {
                Button(
                    onClick = {
                        isDropdownMenuVisibleCalen = !isDropdownMenuVisibleCalen
                        isTextVisible = false // Ocultar el texto cuando se toca el botón
                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text("Seleccionar fecha")
                }

                if (isTextVisible) {
                    Text(text = "Fecha seleccionada: $date", modifier = Modifier.padding(start = 24.dp))
                    diaDialog=date
                }

                DropdownMenu(
                    expanded = isDropdownMenuVisibleCalen,
                    onDismissRequest = {
                        isDropdownMenuVisibleCalen = false
                        isTextVisible = true
                    }
                ) {
                    calendario(date = date) {
                        date = it
                        isDropdownMenuVisibleCalen = false
                        isTextVisible = true
                    }
                }
            }
            //*****************************************************************************************
            Spacer(modifier = Modifier.height(10.dp))
            //*****************************************************************************************
            val contexth = LocalContext.current
            val horasDisp = arrayOf("13:00", "14:15", "14:45", "17:30")
            var expanded3 by remember { mutableStateOf(false) }
            var horaSeleccionada by remember { mutableStateOf("Seleccione un Horario") }

            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded3,
                    onExpandedChange = {
                        expanded3 = !expanded3
                    }
                ) {
                    TextField(
                        value = horaSeleccionada,
                        onValueChange = {},
                        label = {
                            Text(text = "Horario")
                        },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded3) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded3,
                        onDismissRequest = { expanded3 = false }
                    ) {
                        horasDisp.forEach { specialty ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = specialty,
                                    )
                                },
                                onClick = {
                                    horaSeleccionada = specialty
                                    horaDialog=horaSeleccionada
                                    expanded3 = false
                                }
                            )
                        }
                    }
                }
            }
            //*****************************************************************************************************
            Spacer(modifier = Modifier.height(100.dp))
            //*****************************************************************************************************
            Button(

                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text("Sacar Turno")
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Confirmar Turno") },
                 //   text = { Text("Especialidad: General\nHora seleccionada: $horaDialog") },
                    text = {
                        Column {
                            Text(text = "Especialidad: $especialodadDialog")
                            Text(text = "Doctor: $doctorDialog")
                            Text(text = "Dia: $diaDialog", fontWeight = FontWeight.Bold)
                            Text(text = "Hora: $horaDialog", fontWeight = FontWeight.Bold)
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            startActivity(
                                Intent(
                                    this@PantallaSacarTurno, PantallaPrincipal::class.java
                                )
                            )
                            showDialog = false
                        }) {
                            Text("Confirmar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun horarios() {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
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
                onClick = {
                    isDropdownMenuVisible = !isDropdownMenuVisible
                    isTextVisible = false // Ocultar el texto cuando se toca el botón
                },
                modifier = Modifier
                    .padding(20.dp)
                    .background(MaterialTheme.colorScheme.background)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
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