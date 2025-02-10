package com.alex3g.moneyflow.ui.screens.creation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex3g.moneyflow.api.ApiService
import com.alex3g.moneyflow.api.TransactionRequest
import com.alex3g.moneyflow.data.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class CreateTransactionViewModel(
    private val userRepo: UserRepo,
    private val apiService: ApiService
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTransaction(transaction: TransactionRequest) {
        viewModelScope.launch (Dispatchers.IO){
            try {
                val user = userRepo.getLoggedUser()!!
                val transactionReq = transaction.copy(userId = user.id)
                val res = apiService.addTransaction(transactionReq)
                Log.d("TAG", "addTransaction: $res")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}