package com.delasystems.fooddata.presentation.fooddetails

import com.delasystems.fooddata.domain.Food

data class FoodDetailsScreenState(
    val food: Food?,
    val isLoading: Boolean,
    val error: String? = null
)
