package com.example.santepriceindex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Calculate
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Monitor
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.santepriceindex.navigation.Route
import com.example.santepriceindex.ui.screens.CalculatorScreen
import com.example.santepriceindex.ui.screens.DashboardScreen
import com.example.santepriceindex.ui.screens.DigitalSlateScreen
import com.example.santepriceindex.ui.screens.TrendsScreen
import com.example.santepriceindex.ui.theme.SantePriceIndexTheme
import com.example.santepriceindex.ui.viewmodel.PricingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SantePriceIndexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SanteApp()
                }
            }
        }
    }
}

@Composable
fun SanteApp() {
    @Suppress("UNCHECKED_CAST")
    val backStack = rememberNavBackStack(Route.Dashboard as NavKey) as NavBackStack<Route>
    val pricingViewModel: PricingViewModel = viewModel()
    
    val currentRoute = backStack.last()
    val isSlateMode = currentRoute is Route.PriceBoard

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            if (!isSlateMode) {
                appDestinations.forEach { destination ->
                    item(
                        selected = currentRoute == destination.route,
                        onClick = { 
                            if (currentRoute != destination.route) {
                                backStack.removeAt(backStack.size - 1)
                                backStack.add(destination.route)
                            }
                        },
                        icon = { Icon(destination.icon, contentDescription = destination.label) },
                        label = { Text(destination.label) }
                    )
                }
            }
        },
        layoutType = if (isSlateMode) NavigationSuiteType.None else NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo())
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = { 
                if (backStack.size > 1) {
                    backStack.removeAt(backStack.size - 1)
                }
            },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator<Route>(),
                rememberViewModelStoreNavEntryDecorator<Route>()
            ),
            entryProvider = entryProvider {
                entry<Route.Dashboard> {
                    DashboardScreen(
                        onNavigateToCalculator = { 
                            backStack.removeAt(backStack.size - 1)
                            backStack.add(Route.Calculator) 
                        },
                        onNavigateToPriceBoard = { 
                            backStack.removeAt(backStack.size - 1)
                            backStack.add(Route.PriceBoard) 
                        },
                        onNavigateToTrends = { 
                            backStack.removeAt(backStack.size - 1)
                            backStack.add(Route.Trends) 
                        }
                    )
                }
                entry<Route.Calculator> {
                    CalculatorScreen(
                        viewModel = pricingViewModel,
                        onNavigateBack = { 
                            backStack.removeAt(backStack.size - 1)
                            backStack.add(Route.Dashboard)
                        },
                        onNavigateToSlate = { backStack.add(Route.PriceBoard) }
                    )
                }
                entry<Route.PriceBoard> {
                    DigitalSlateScreen(
                        viewModel = pricingViewModel,
                        onClose = { backStack.removeAt(backStack.size - 1) }
                    )
                }
                entry<Route.Trends> {
                    TrendsScreen(
                        onNavigateBack = { 
                            backStack.removeAt(backStack.size - 1)
                            backStack.add(Route.Dashboard)
                        }
                    )
                }
            }
        )
    }
}

data class AppDestination(
    val route: Route,
    val label: String,
    val icon: ImageVector
)

val appDestinations = listOf(
    AppDestination(Route.Dashboard, "Mandi", Icons.Rounded.Dashboard),
    AppDestination(Route.Calculator, "Calc", Icons.Rounded.Calculate),
    AppDestination(Route.Trends, "Trends", Icons.Rounded.TrendingUp)
)

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "$name Screen (Coming Soon)", 
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
