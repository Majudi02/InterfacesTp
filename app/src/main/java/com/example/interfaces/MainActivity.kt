package com.example.interfaces

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfaces.ui.theme.InterfacesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfacesTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val contextAplication= LocalContext.current.applicationContext
                    LoginScreen(contextAplication)

                }
            }
        }
    }
}

@SuppressLint("PrivateResource")
@Composable
fun LoginScreen(contextAplication: Context) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "Bienvenido a la veterinaria",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 23.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge

            )
        Text(text = "Nombre",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 23.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painterResource(id = androidx.core.R.drawable.ic_call_answer_low), contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(46.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text("DNI") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text("Contraseña") },
        )

        Spacer(modifier = Modifier.height(36.dp))

        Button(
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.AzulApp)),
            onClick = {
                val intent = Intent(contextAplication, PantallaSelecionarMascota::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                contextAplication.startActivity(intent)
                      },


            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text("Iniciar sesion")
        }

        Spacer(modifier = Modifier.height(66.dp))


    }
}




