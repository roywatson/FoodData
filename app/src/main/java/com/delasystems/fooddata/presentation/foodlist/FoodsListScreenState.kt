package com.delasystems.fooddata.presentation.foodlist

import androidx.paging.PagingData
import com.delasystems.fooddata.domain.Food
import kotlinx.coroutines.flow.Flow

data class FoodsListScreenState(
    var foodsFlow: Flow<PagingData<Food>>,
    var totalHits: Long = -1,
    val isLoading: Boolean,
    val error: String? = null
)
