package com.alex3g.moneyflow.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alex3g.moneyflow.R
import com.alex3g.moneyflow.api.Transaction


@Composable
fun TransactionCard(
    transaction: Transaction,
    onTransactionClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(horizontal = 12.dp, vertical = 6.dp)
            .height(70.dp)
            .clickable { onTransactionClick(transaction.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val isIncome: Boolean = true

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_attach_money),
                    contentDescription = null,
                    tint = if (isIncome) Color(0xFF81C784) else Color(0xFFFF8A65),
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = if (isIncome) Color(0xFFE8F5E9) else Color(0xFFFFF3E0),
                            shape = CircleShape
                        )
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = transaction.description,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF424242)
                    )
                    Text(
                        text = transaction.type,
                        color = Color.Gray
                    )

                }
            }

            Text(
                text = if (isIncome) "+ kz ${transaction.amount}" else "- kz ${transaction.amount}",
                fontWeight = FontWeight.Bold,
                color = if (true) Color(0xFF388E3C) else Color(
                    0xFFD84315
                )
            )
        }
    }
}