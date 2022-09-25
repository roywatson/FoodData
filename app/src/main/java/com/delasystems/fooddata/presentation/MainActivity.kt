package com.delasystems.fooddata.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delasystems.fooddata.R
import com.delasystems.fooddata.navigation.NavItem
import com.delasystems.fooddata.presentation.fooddetails.FoodDetailsScreen
import com.delasystems.fooddata.presentation.foodfavorites.FoodFavoritesScreen
import com.delasystems.fooddata.presentation.foodhistory.FoodHistoryScreen
import com.delasystems.fooddata.presentation.foodlist.FoodsListScreen
import com.delasystems.fooddata.presentation.search.FoodSearchScreen
import com.delasystems.fooddata.ui.theme.FoodDataTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDataTheme {
                FoodDataApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoodDataApp() {
    val navController = rememberNavController()
    val viewModel: SharedViewModel = hiltViewModel()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
                viewModel.items.forEachIndexed() { index, navItem ->
                    val sel = navItem.routes.any { currentRoute.equals(it)}
                    NavigationBarItem(
                        selected = navItem.routes.any { currentRoute.equals(it)} ?: false,
                        onClick = { navController.navigate(navItem.routes[0]) },
                        icon = { Icon(painterResource(id = navItem.icon),
                            contentDescription = navItem.label) },
                        label = { Text(navItem.label) },
                        alwaysShowLabel = true,
                        colors = NavigationBarItemDefaults.colors()
                    )
                }
            }
        }
    ) {

        NavHost(navController, startDestination = NavItem.SearchScreen.routes[0]) {
            composable(route = "foodsearch") {
                val context = LocalContext.current
                FoodSearchScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = viewModel.stateFoodSearch.value,
                    onSearch = { any, all, none ->
                        navController.navigate("foodslist")
                        viewModel.searchAdvanced(any, all, none)
                    },
                    onClear = { viewModel.clearMostRecentSearch() }
                )
            }
            composable(route = "foodslist") {
                FoodsListScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = viewModel.stateFoodsList.value,
                    onItemClick = { food ->
                        navController.navigate("fooddetails")
                        viewModel.setCurrentFood(food)
                    }
                )
            }
            composable(route = "fooddetails") {
                FoodDetailsScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = viewModel.stateFoodDetails.value
                )
            }
            composable(route = "foodhistory") {
                FoodHistoryScreen()
            }
            composable(route = "foodfavorites") {
                FoodFavoritesScreen()
            }
        }
    }
}

