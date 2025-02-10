package com.alex3g.moneyflow.ui.screens.login

import CustomOutlinedTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex3g.moneyflow.R

@Composable
fun RegisterScreen(
    viewmodel: AuthViewModel,
    onLogin: () -> Unit

) {
    var name by remember { mutableStateOf("tester") }
    var email by remember { mutableStateOf("test@test.com") }
    var password by remember { mutableStateOf("test@test.com") }

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
            value = name,
            placeholder = "Insira seu nome",
            onValueChange = { name = it },
            label = "Nome",
            iconRes = R.drawable.ic_account_circle,
            keywordType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(10.dp))

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
                viewmodel.cadastrar(name, email, password)
                onLogin()
            },
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Criar conta")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = onLogin) {
            Text("Fazer login", color = Color.Gray)
        }
    }
}
