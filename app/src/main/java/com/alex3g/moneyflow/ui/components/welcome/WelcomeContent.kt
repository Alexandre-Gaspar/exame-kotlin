package com.alex3g.moneyflow.ui.components.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex3g.moneyflow.R

@Composable
fun WelcomeContent(modifier: Modifier = Modifier) {
    Column(
        //verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(text = "Funcionalidades:", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(24.dp))
        WelcomeHowItWorkTip(
            modifier = Modifier.fillMaxWidth(),
            title = "Controlar seus gastos",
            subtitle = "Você pode controlar os seus gastos, receitas e despesas",
            iconRes = R.drawable.ic_money
        )
        Spacer(modifier = Modifier.height(12.dp))
        WelcomeHowItWorkTip(
            modifier = Modifier.fillMaxWidth(),
            title = "Visualizar relatórios",
            subtitle = "Você pode controlar os seus gastos, receitas e despesas",
            iconRes = R.drawable.ic_monitoring
        )
    }
}