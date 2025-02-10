package com.alex3g.moneyflow.ui.screens.login

import CustomOutlinedTextField
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alex3g.moneyflow.R
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    viewmodel: AuthViewModel,
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("test@test.com") }
    var password by remember { mutableStateOf("test@test.com") }

    LaunchedEffect(viewmodel.logged.value) {
        if (viewmodel.logged.value) {
            onLogin()
        }
    }

    LaunchedEffect(viewmodel.error.value) {
        if (viewmodel.error.value) {
            Toast.makeText(
                context,
                "E-mail ou senha incorretos",
                Toast.LENGTH_SHORT
            ).show()
            delay(300)
            viewmodel.resetError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_login_orange),
            contentDescription = "Logo de usuário",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = email,
            placeholder = "Insira seu e-mail",
            onValueChange = { email = it },
            label = "E-mail",
            iconRes = R.drawable.ic_alternate_email,
            keywordType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = password,
            placeholder = "Insira sua senha",
            onValueChange = { password = it },
            label = "Senha",
            iconRes = R.drawable.ic_lock,
            keywordType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                viewmodel.login(email, password)
            },
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = onRegister) {
            Text("Criar conta", color = Color.Gray)
        }


    }
}

