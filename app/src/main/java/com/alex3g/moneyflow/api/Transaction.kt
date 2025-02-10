package com.alex3g.moneyflow.api

data class Transaction (
    val id: Int,
    val description: String,
    val amount: Double,
    val type: String,
)

data class TransactionRequest (
    val description: String,
    val amount: Double,
    val category: Int = 0,
    val type: String = "EXPENSE",
    val userId: Int
)
