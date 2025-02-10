package com.alex3g.moneyflow.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex3g.moneyflow.api.ApiService
import com.alex3g.moneyflow.api.Transaction
import com.alex3g.moneyflow.data.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepo: UserRepo,
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Transaction>> = MutableStateFlow(emptyList())
    val uiState = _uiState.asStateFlow()

    init {
        loadTransactions()
    }

    fun loadTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val transactions = apiService.getTransactions()
                _uiState.value = transactions
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteTransaction(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            //userRepo.deleteTransaction(id)
        }
    }
}
