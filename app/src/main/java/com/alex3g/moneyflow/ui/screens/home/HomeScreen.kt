package com.alex3g.moneyflow.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex3g.moneyflow.R
import com.alex3g.moneyflow.ui.components.home.BlockCard
import com.alex3g.moneyflow.ui.components.home.TransactionCard

@SuppressLint("Range")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToCreateTransaction: () -> Unit,
    onTransactionClick: (Int) -> Unit
) {
    val transactions = viewModel.uiState.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MoneyFlow") },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color(0xFFFFA500)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFFFFA500),
                contentColor = Color.White,
                shape = CircleShape,
                onClick = onNavigateToCreateTransaction
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Resumo",
                modifier = Modifier.padding(bottom = 16.dp),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BlockCard(
                    modifier = Modifier.weight(1f),
                    title = "Saldo",
                    iconRes = R.drawable.ic_attach_money,
                    balance = 77.9
                )
                BlockCard(
                    modifier = Modifier.weight(1f),
                    title = "Receitas",
                    iconRes = R.drawable.ic_arrow_circle_down,
                    balance = 565.9
                )
                BlockCard(
                    modifier = Modifier.weight(1f),
                    title = "Despesas",
                    iconRes = R.drawable.ic_arrow_circle_up,
                    balance = 56.444
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Transações Recentes",
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp),
            )

            if (transactions.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(transactions) { transaction ->
                        TransactionCard(
                            transaction = transaction,
                            onTransactionClick = { onTransactionClick(transaction.id) }
                        )
                    }
                }
            }
        }
    }
}