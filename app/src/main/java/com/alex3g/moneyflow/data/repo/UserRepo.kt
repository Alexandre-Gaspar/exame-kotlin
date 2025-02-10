package com.alex3g.moneyflow.data.repo

import com.alex3g.moneyflow.data.dao.UserDao
import com.alex3g.moneyflow.data.models.User
import kotlinx.coroutines.flow.Flow

class UserRepo(
    private val userDao: UserDao
) {
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }

    suspend fun updateUserLoginState(userId: Int, isLoggedIn: Boolean) {
        return userDao.updateUserLoginState(userId, isLoggedIn)
    }

    suspend fun getLoggedUser(): User? {
        return userDao.getLoggedInUser()
    }

    suspend fun getLoggedInUserFlow(): Flow<User?> {
        return userDao.getLoggedInUserFlow()
    }
}
