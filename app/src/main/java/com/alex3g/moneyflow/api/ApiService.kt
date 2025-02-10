package com.alex3g.moneyflow.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("transactions")
    suspend fun getTransactions(): List<Transaction>

    @POST("transactions")
    suspend fun addTransaction(@Body transaction: TransactionRequest): Transaction

    @DELETE("transactions/{id}")
    suspend fun deleteTransaction(id: Int)

    @GET("transactions/{id}")
    suspend fun getTransactionById(id: Int): Transaction
}