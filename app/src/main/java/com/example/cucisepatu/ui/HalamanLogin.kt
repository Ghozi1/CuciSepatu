package com.example.cucisepatu.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cucisepatu.R
import com.example.cucisepatu.navigation.DestinasiNavigasi
import com.example.cucisepatu.ui.home.DestinasiHome
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object DestinasiLogin : DestinasiNavigasi {
    override val route = "Login"
    override val titleRes = "LoginP"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController
) {
    val context = LocalContext.current
    lateinit var auth: FirebaseAuth
    auth = Firebase.auth
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.30f), Alignment.TopCenter,
    ){

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.padding(40.dp))
        Image(painter = painterResource(
            id = R.drawable.sikatsikil),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = emailText,
            onValueChange = { emailText = it },
            label = { Text(text = "Masukkan email anda") },
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email Icon"
                    )
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(vertical = 5.dp)
        )
        OutlinedTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            label = { Text(text = "Masukkan password anda") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Pass Icon")
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                (
                        auth.signInWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    navController.navigate(DestinasiHome.route)
                                    Toast.makeText(context, "Login success", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                Color.Black
            )
        )
        {
            Text(text = "Silahkan Masuk")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}