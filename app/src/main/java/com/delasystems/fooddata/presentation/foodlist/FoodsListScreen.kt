package com.delasystems.fooddata.presentation.foodlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.delasystems.fooddata.domain.Food

@Composable
fun FoodsListScreen(
    modifier: Modifier = Modifier,
    state: FoodsListScreenState,
    onItemClick: (food: Food) -> Unit,
) {
    Surface(
        modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier) {
            val foodsFlow = state.foodsFlow
            val lazyFoodItems: LazyPagingItems<Food> = foodsFlow.collectAsLazyPagingItems()
            Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                LazyColumn(
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 8.dp
                    )
                ) {
                    itemsIndexed(lazyFoodItems) { index, food ->
                        FoodsListItem(index = index, hits = state.totalHits, onItemClick = onItemClick, item = food ?: Food())
                    }
                }
            }
        }
    }
}

@Composable
fun FoodsListItem( index: Int, hits: Long, onItemClick: (food: Food) -> Unit, item: Food) {
    Card(
        elevation = CardDefaults.cardElevation(),

        modifier = Modifier
            .padding(8.dp)
            .height(120.dp)
            .clickable { onItemClick(item) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "${(index + 1)} / ${item.totalHits}" ,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .weight(0.2f)
                    .padding(8.dp)
            )
            Column(modifier = Modifier.weight(0.8f)) {
                Text(
                    text = item.description ?: "n/a",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = item.brandName ?: "n/a",
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}

