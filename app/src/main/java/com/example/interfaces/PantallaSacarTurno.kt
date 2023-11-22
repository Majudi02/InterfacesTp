package com.example.interfaces

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale


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
                .verticalScroll(rememberScrollState()),
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
                    color= Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 15.dp, end = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            SelectEspecialidad()
            Spacer(modifier = Modifier.height(10.dp))
            SelectDoctor()
            Spacer(modifier = Modifier.height(10.dp))
            //test()
            DatePickerDisplayModeInput()
            Spacer(modifier = Modifier.height(10.dp))
            horarios()

            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = {
                    /*  */
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text("Sacar Turno")
            }

        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerSimple(){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Pre-select a date for January 4, 2020
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
            DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

            Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerDisplayModeInput(){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val state = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis(),initialDisplayMode = DisplayMode.Input)
            DatePicker(state = state, modifier = Modifier.padding(16.dp))

            Text("Entered date timestamp: ${(state.selectedDateMillis) ?: "no input"}")
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


    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun test() {
        var date by remember { mutableStateOf("") }
        var isDropdownMenuVisible by remember { mutableStateOf(false) }
        var isTextVisible by remember { mutableStateOf(false) }


        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

            val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)

            val snackState = remember { SnackbarHostState() }
            val snackScope = rememberCoroutineScope()
            SnackbarHost(hostState = snackState, Modifier)
            val openDialog = remember { mutableStateOf(true) }
            if (openDialog.value) {
                val datePickerState = rememberDatePickerState()
                val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }

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

                LaunchedEffect(datePickerState.selectedDateMillis) {
                    date = datePickerState.selectedDateMillis?.let { timestamp ->
                        val selectedDate = Date(timestamp)
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        dateFormat.format(selectedDate)
                    } ?: ""
                }

                /*     if (isTextVisible) {
                    Text(
                        text = "Fecha seleccionada: $date",
                        modifier = Modifier.padding(start = 24.dp)
                    )
                } */

                DropdownMenu(
                    expanded = isDropdownMenuVisible,
                    onDismissRequest = {
                        isDropdownMenuVisible = false
                        isTextVisible = true
                    }
                ) {
                    DatePickerDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        confirmButton = {
                            /*       TextButton(
                                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                                onClick = {
                                    openDialog.value = false
                                    snackScope.launch {
                                        snackState.showSnackbar(
                                            "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                                        )
                                    }
                                },
                                enabled = confirmEnabled.value
                            ) {*/
                            isTextVisible = true
                            Text(
                                "OK",
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .background(color = colorResource(id = androidx.appcompat.R.color.abc_background_cache_hint_selector_material_light)),
                                color = Color.Black
                            )
                            //     }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    openDialog.value = false
                                }
                            ) {
                                Text(
                                    "Cancel",
                                    Modifier.background(MaterialTheme.colorScheme.primary),
                                    color = Color.Black
                                )
                            }
                        }
                    ) {
                        DatePicker(
                            state = datePickerState,
                            modifier = Modifier.padding(16.dp),
                            showModeToggle = false
                        )
                    }

                }
            }

            if (isTextVisible) {
                Text("Fecha seleccionada: $date", modifier = Modifier.padding(start = 26.dp))
            }
        }

    }


    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Calendario2() {

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            /*Button(

                onClick = {
                    isDropdownMenuVisible = !isDropdownMenuVisible
                    isTextVisible = false // Ocultar el texto cuando se toca el botón
                },
                modifier = Modifier.padding(20.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text("Seleccionar fecha")
            }*/
            val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
            // Decoupled snackbar host state from scaffold state for demo purposes.
            val snackState = remember { SnackbarHostState() }
            val snackScope = rememberCoroutineScope()
            SnackbarHost(hostState = snackState, Modifier)
            val openDialog = remember { mutableStateOf(true) }
// TODO demo how to read the selected date from the state.
            if (openDialog.value) {
                val datePickerState = rememberDatePickerState()
                val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
                DatePickerDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onDismissRequest.
                        openDialog.value = false
                    },
                    confirmButton = {
                        TextButton(
                            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                            onClick = {
                                openDialog.value = false
                                snackScope.launch {
                                    snackState.showSnackbar(
                                        "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                                    )
                                }
                            },
                            enabled = confirmEnabled.value
                        ) {
                            Text(
                                "OK",
                                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary),
                                color = Color.White
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text("Cancel", Modifier.background(MaterialTheme.colorScheme.primary))
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState,
                        modifier = Modifier.padding(16.dp),
                        showModeToggle = false
                    )
                }
            }


            Text("Entered date timestamp: ${state.selectedDateMillis ?: "no input"}")
        }

        /*val datePickerState = rememberDatePickerState(
            selectableDates = object : SelectableDates {
                // Blocks Sunday and Saturday from being selected.
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val dayOfWeek = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                            .toLocalDate().dayOfWeek
                        dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY
                    } else {
                        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                        calendar.timeInMillis = utcTimeMillis
                        calendar[Calendar.DAY_OF_WEEK] != Calendar.SUNDAY &&
                                calendar[Calendar.DAY_OF_WEEK] != Calendar.SATURDAY
                    }
                }

                // Allow selecting dates from year 2023 forward.
                override fun isSelectableYear(year: Int): Boolean {
                    return year > 2022
                }
            }
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            DatePicker(state = datePickerState,showModeToggle= false,)
            Text("Seleccionar fecha: ${datePickerState.selectedDateMillis ?: "no selection"}")
        }*/
    }


    @OptIn(ExperimentalMaterial3Api::class)
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
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.background
                ),
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
        Column() {

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
