package com.alex3g.moneyflow

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alex3g.moneyflow.api.retrofitService
import com.alex3g.moneyflow.data.db.AppDatabase
import com.alex3g.moneyflow.data.repo.UserRepo
import com.alex3g.moneyflow.ui.screens.creation.CreateTransactionScreen
import com.alex3g.moneyflow.ui.screens.creation.CreateTransactionViewModel
import com.alex3g.moneyflow.ui.screens.detail.DetailScreen
import com.alex3g.moneyflow.ui.screens.detail.DetailsViewModel
import com.alex3g.moneyflow.ui.screens.home.HomeScreen
import com.alex3g.moneyflow.ui.screens.home.HomeViewModel
import com.alex3g.moneyflow.ui.screens.login.AuthViewModel
import com.alex3g.moneyflow.ui.screens.login.LoginScreen
import com.alex3g.moneyflow.ui.screens.login.RegisterScreen
import com.alex3g.moneyflow.ui.screens.route.CreateTransactionRoute
import com.alex3g.moneyflow.ui.screens.route.DetailRoute
import com.alex3g.moneyflow.ui.screens.route.HomeRoute
import com.alex3g.moneyflow.ui.screens.route.LoginRoute
import com.alex3g.moneyflow.ui.screens.route.RegisterRoute
import com.alex3g.moneyflow.ui.screens.route.WelcomeRoute
import com.alex3g.moneyflow.ui.screens.welcome.WelcomeScreen
import com.alex3g.moneyflow.ui.theme.MoneyFlowTheme

class MainActivity : ComponentActivity() {

    private lateinit var userRepo: UserRepo
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var createTransactionViewModel: CreateTransactionViewModel
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var authViewModel: AuthViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userDao = AppDatabase.getDatabase(this).transactionDao()
        val apiService = retrofitService()
        userRepo = UserRepo(userDao)
        homeViewModel = HomeViewModel(userRepo, apiService)
        createTransactionViewModel = CreateTransactionViewModel(userRepo, apiService)

        authViewModel = AuthViewModel(userDao)

        enableEdgeToEdge()
        setContent {
            MoneyFlowTheme {
                var navController = rememberNavController()
                val user = authViewModel.user.value
                val startScreen = if (user != null) HomeRoute else WelcomeRoute

                NavHost(
                    navController = navController,
                    startDestination = startScreen
                ) {
                    composable<WelcomeRoute> {
                        WelcomeScreen(
                            onNavigateToHome = {
                                navController.navigate(LoginRoute)
                            }
                        )
                    }
                    composable<LoginRoute> {
                        LoginScreen(
                            viewmodel = authViewModel,
                            onLogin = { navController.navigate(HomeRoute) },
                            onRegister = { navController.navigate(RegisterRoute) }
                        )
                    }
                    composable<RegisterRoute> {
                        RegisterScreen(
                            viewmodel = authViewModel,
                            onLogin = { navController.navigate(LoginRoute) }
                        )
                    }
                    composable<HomeRoute> {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onNavigateToCreateTransaction = { navController.navigate(CreateTransactionRoute) },
                            onTransactionClick = { transactionId ->
                                navController.navigate(DetailRoute(transactionId))
                            }
                        )
                    }
                    composable<DetailRoute> { backStackEntry ->
                        val transactionId = backStackEntry.toRoute<DetailRoute>().transactionId
                        detailsViewModel = DetailsViewModel(userRepo, apiService, transactionId)
                        DetailScreen(
                            viewModel = detailsViewModel,
                            onNavigateBack = { navController.navigate(HomeRoute) }
                        )
                    }
                    composable<CreateTransactionRoute> {
                        CreateTransactionScreen(
                            viewModel = createTransactionViewModel,
                            onNavigateBack = {
                                navController.navigate(HomeRoute)
                            }
                        )
                    }
                }
            }
        }
    }
}