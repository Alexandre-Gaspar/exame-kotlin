package com.alex3g.moneyflow.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex3g.moneyflow.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailsViewModel,
    onNavigateBack: () -> Unit
) {

    val transaction = viewModel.transactionDetails.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Transação", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0288D1)),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            transaction?.let {

                Text(
                    text = it.description,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0288D1),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Icon(
                        painter =
                        if (it.type.toString() == "EXPENSE")
                            painterResource(id = R.drawable.ic_arrow_circle_up)
                        else painterResource(id = R.drawable.ic_arrow_circle_down),
                        contentDescription = "Transaction Type",
                        tint = if (it.type.toString() == "EXPENSE") Color(0xFFFFB74D) else Color(0xFF81C784),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tipo: ${it.type.toString()}",
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    text = "Valor: ${it.amount}",
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "09/06/2025",
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Descrição",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = it.description ?: "Nunhuma descrição",
                    color = Color.Gray,
                    lineHeight = 20.sp
                )

            }
        }
    }
}