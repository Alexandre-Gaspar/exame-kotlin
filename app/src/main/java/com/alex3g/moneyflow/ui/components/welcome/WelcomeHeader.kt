package com.alex3g.moneyflow.ui.components.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex3g.moneyflow.R

@Composable
fun WelcomeHeader() {
    Column/*(verticalArrangement = Arrangement.spacedBy(24.dp))*/ {
        Icon(
            modifier = Modifier.size(56.dp),
            painter = painterResource(id = R.drawable.ic_paid),
            contentDescription = "Imagem de moeda",
            tint = Color(0xFF0288D1)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Boas vindas ao MoneyFlow",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Organize seus gastos e conquiste seus objetivos financeiros.",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
    }
}