package com.alex3g.moneyflow.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alex3g.moneyflow.data.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun addUser(note: User)

    @Query("SELECT * FROM users WHERE email = :email and password = :password")
    suspend fun login(email: String, password: String): User?

    @Query("UPDATE users SET isLoggedIn = :isLoggedIn WHERE id = :userId")
    suspend fun updateUserLoginState(userId: Int, isLoggedIn: Boolean)

    @Query("SELECT * FROM users WHERE isLoggedIn = 1")
    suspend fun getLoggedInUser(): User?

    @Query("SELECT * FROM users WHERE isLoggedIn = 1")
    fun getLoggedInUserFlow(): Flow<User?>

}