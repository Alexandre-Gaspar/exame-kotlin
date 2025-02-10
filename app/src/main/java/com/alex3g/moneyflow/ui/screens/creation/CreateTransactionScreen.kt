package com.alex3g.moneyflow.ui.screens.creation

import CustomOutlinedTextField
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.dp
import com.alex3g.moneyflow.R
import com.alex3g.moneyflow.api.TransactionRequest
import com.alex3g.moneyflow.ui.components.button.MoneyFlowButton
import com.alex3g.moneyflow.ui.components.transaction.TransactionTypeButton

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTransactionScreen(
    viewModel: CreateTransactionViewModel,
    onNavigateBack: () -> Unit
) {
    var uiState by remember { mutableStateOf(
        TransactionRequest(
            description = "",
            amount = 0.0,
            type = "",
            userId = 0
        )
    ) }

    //val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nova Transação", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFFFA500)),
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
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = uiState.description,
                    onValueChange = { uiState = uiState.copy(description = it) },
                    label = { Text("Descrição") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Descrição"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.amount.toString(),
                    onValueChange = { uiState = uiState.copy(amount = it.toDoubleOrNull() ?: 0.0) },
                    label = { Text("Valor") },
                    leadingIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_attach_money),
                            contentDescription = "Valor"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.type,
                    onValueChange = { uiState = uiState.copy(type = it) },
                    label = { Text("Tipo") },
                    leadingIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_attach_money),
                            contentDescription = "Valor"
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
//
//                CustomOutlinedTextField(value = amount, placeholder = "Valor", onValueChange = { amount = it }, iconRes = R.drawable.ic_attach_money, keywordType = KeyboardType.Text)
//                CustomOutlinedTextField(value = description, placeholder = "descrição", onValueChange = { description = it }, iconRes = R.drawable.ic_info, keywordType = KeyboardType.Text)
//
//                OutlinedTextField(
//                    value = description,
//                    onValueChange = { description = it },
//                    label = { Text("Descrição") },
//                    leadingIcon = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_info),
//                            contentDescription = "Título"
//                        )
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 8.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    TransactionTypeButton(
//                        text = "Receita",
//                        isSelected = selectedType == TransactionType.INCOME,
//                        onClick = { selectedType = TransactionType.INCOME }
//                    )
//                    Text(text = "ou")
//                    TransactionTypeButton(
//                        text = "Despesa",
//                        isSelected = selectedType == TransactionType.EXPENSE,
//                        onClick = { selectedType = TransactionType.EXPENSE }
//                    )
//                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MoneyFlowButton(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(56.dp),
                    text = "Guardar",
                    onClick = {
                        viewModel.addTransaction(uiState)
                        onNavigateBack()
                    }
                )
            }
        }
    }
}
