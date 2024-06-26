package com.example.trbofficerandroid.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.trbofficerandroid.presentation.ui.screen.account.AccountScreen
import com.example.trbofficerandroid.presentation.ui.screen.addclient.AddClientScreen
import com.example.trbofficerandroid.presentation.ui.screen.addofficer.AddOfficerScreen
import com.example.trbofficerandroid.presentation.ui.screen.addtariff.AddTariffScreen
import com.example.trbofficerandroid.presentation.ui.screen.application.ApplicationScreen
import com.example.trbofficerandroid.presentation.ui.screen.client.ClientScreen
import com.example.trbofficerandroid.presentation.ui.screen.home.HomeScreen
import com.example.trbofficerandroid.presentation.ui.screen.loan.LoanScreen
import com.example.trbofficerandroid.presentation.ui.screen.officer.OfficerScreen
import com.example.trbofficerandroid.presentation.ui.screen.signin.SignInScreen
import com.example.trbofficerandroid.presentation.ui.screen.tariff.TariffScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize()
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            HomeScreen(rootNavController = navController)
        }
        composable(
            route = "${Screen.Tariff.route}/{id}/{additionDate}/{name}/{description}/{interestRate}/{officerId}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("additionDate") { type = NavType.LongType },
                navArgument("name") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("interestRate") { type = NavType.FloatType },
                navArgument("officerId") { type = NavType.StringType },
            ),
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            TariffScreen(
                navigateBack = { navController.popBackStack() },
                navigateToOfficer = { officerId ->
                    navController.navigate("${Screen.Officer.route}/$officerId") {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = "${Screen.Client.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            ClientScreen(
                onBackClick = { navController.popBackStack() },
                navigateToAccount = {
                    navController.navigate("${Screen.Account.route}/$it") {
                        launchSingleTop = true
                    }
                },
                navigateToLoan = {
                    navController.navigate("${Screen.Loan.route}/$it") {
                        launchSingleTop = true
                    }
                },
                showOfficer = {
                    navController.navigate("${Screen.Officer.route}/$it")
                }
            )
        }
        composable(
            route = "${Screen.Officer.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            OfficerScreen(
                navigateBack = { navController.popBackStack() },
                showOfficer = {
                    navController.navigate("${Screen.Officer.route}/$it")
                }
            )
        }
        composable(
            route = Screen.AddClient.route,
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            AddClientScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.AddOfficer.route,
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            AddOfficerScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.AddTariff.route,
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            AddTariffScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Screen.Account.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            AccountScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.SignIn.route,
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
            deepLinks = listOf(
                navDeepLink { uriPattern = "trust-bank://sign-in/{token}" },
                navDeepLink { uriPattern = "trust-bank://logout" },
            ),
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            SignInScreen(
                navigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        launchSingleTop = true
                        popUpTo(Screen.SignIn.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = "${Screen.Application.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            ApplicationScreen(
                navigateBack = { navController.popBackStack() },
                navigateToClient = {
                    navController.navigate("${Screen.Client.route}/$it") {
                        launchSingleTop = true
                    }
                },
                navigateToTariff = {
                    with(it) {
                        navController.navigate("${Screen.Tariff.route}/$id/$additionDate/$name/$description/$interestRate/$officerId") {
                            launchSingleTop = true
                        }
                    }
                },
                navigateToOfficer = {
                    navController.navigate("${Screen.Officer.route}/$it") {
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(
            route = "${Screen.Loan.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = { enterTransition() },
            popEnterTransition = { popEnterTransition() },
            exitTransition = { exitTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            LoanScreen(
                onTariffClick = {
                    with(it) {
                        navController.navigate("${Screen.Tariff.route}/$id/$additionDate/$name/$description/$interestRate/$officerId") {
                            launchSingleTop = true
                        }
                    }
                },
                onShowAccountClick = {
                    navController.navigate("${Screen.Account.route}/$it") {
                        launchSingleTop = true
                    }
                },
                navigateBack = { navController.popBackStack() },
            )
        }
    }
}

/**
 * Shows transition animation after navigating to screen.
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )
}

/**
 * Shows transition animation after navigating from screen.
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        targetOffset = { it / 5 }
    )
}

/**
 * Shows transition animation after navigating to screen.
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        initialOffset = { it / 5 }
    )
}

/**
 * Shows transition animation after navigating from screen.
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )
}