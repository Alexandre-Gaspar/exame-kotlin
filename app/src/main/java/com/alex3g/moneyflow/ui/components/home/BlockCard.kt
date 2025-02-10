package com.alex3g.moneyflow.ui.components.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BlockCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes iconRes: Int,
    balance: Double
) {
    Card(
        modifier = modifier.fillMaxWidth().height(80.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor =
            if (title.equals("despesas", true)) {
                Color(0xFFFF8A65)
            } else if (title.equals("Receitas", true)) {
                Color(0xFF81C784)
            } else {
                Color(0xFFFFFFFF)
            }
        ),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title)
            Icon(
                painterResource(id = iconRes),
                contentDescription = "Ícone de crédito",
                modifier = Modifier.size(32.dp),
            )
            Text(text = "kz $balance")
        }
    }
}