package com.alex3g.moneyflow.ui.screens.route

import kotlinx.serialization.Serializable

@Serializable
data object WelcomeRoute

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

@Serializable
data object HomeRoute

@Serializable
data object CreateTransactionRoute

@Serializable
data class DetailRoute(val transactionId: Int)

