package com.delasystems.fooddata.navigation

import com.delasystems.fooddata.R

sealed class NavItem(var label: String, var icon: Int, var routes: List<String>) {
    object SearchScreen: NavItem("Search", R.drawable.ic_baseline_search_24, listOf("foodsearch", "foodslist", "fooddetails"))
    object HistoryScreen: NavItem("History", R.drawable.ic_baseline_history_24, listOf("foodhistory"))
    object FavoritesScreen: NavItem("Favorites", R.drawable.ic_baseline_favorite_border_24, listOf("foodfavorites"))
}

