package com.alex3g.moneyflow.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex3g.moneyflow.data.dao.UserDao
import com.alex3g.moneyflow.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userDao: UserDao
): ViewModel() {

    val user = mutableStateOf<User?>(null)
    val error = mutableStateOf(false)
    val logged = mutableStateOf(false)

    val loggedUser = userDao.getLoggedInUserFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            user.value = userDao.getLoggedInUser()
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.login(email, password)
            if (user != null) {
                userDao.updateUserLoginState(user.id, true)
                logged.value = true
            } else {
                error.value = true
            }
        }
    }

    fun resetError() {
        error.value = false
    }

    fun cadastrar(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.addUser(User(name = name, email = email, password = password))
        }
    }

}