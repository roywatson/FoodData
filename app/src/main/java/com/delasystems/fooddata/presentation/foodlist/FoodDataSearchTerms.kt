package com.delasystems.fooddata.presentation.foodlist

data class FoodDataSearchTerms(
    val description: String? = null,
    val page: Int = 1,
    val count: Int = 25,
    val sortBy: String? = null,
    val sortOrder: String? = null,
    val brandOwner: String? = null,
    val dataType: List<String>? = null
)
