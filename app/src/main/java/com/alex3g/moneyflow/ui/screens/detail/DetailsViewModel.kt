package com.alex3g.moneyflow.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex3g.moneyflow.api.ApiService
import com.alex3g.moneyflow.api.Transaction
import com.alex3g.moneyflow.data.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val userRepo: UserRepo,
    private val apiService: ApiService,
    private val transactionId: Int
) : ViewModel() {

    private val _transactionDetails = MutableStateFlow<Transaction?>(null)
    val transactionDetails: StateFlow<Transaction?> = _transactionDetails

    init {
        fetchNoteDetails()
    }

    fun fetchNoteDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val note = apiService.getTransactionById(transactionId)
                _transactionDetails.value = note
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}