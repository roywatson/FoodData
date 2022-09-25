package com.delasystems.fooddata.presentation.fooddetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.delasystems.fooddata.domain.Nutrient


@Composable
fun FoodDetailsScreen(
    modifier: Modifier = Modifier,
    state: FoodDetailsScreenState
) {
    Surface(
        modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier) {
            Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Desc: ${state.food?.description}")
            }
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            ) {
                itemsIndexed(state.food?.foodNutrients ?: emptyList<Nutrient>()) { index, nutrient ->
                    FoodNutrientsItem(index = index, count = state.food?.foodNutrients?.size ?: 0, item = nutrient)
                }
            }
        }
    }
}

@Composable
fun FoodNutrientsItem( index: Int, count: Int, item: Nutrient) {
    Card(
        elevation = CardDefaults.cardElevation(),

        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = item.nutrientName ?: "n/a",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.value.toString() ?: "n/a",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
            Text(
                text = item.unitName ?: "",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
        }
    }
}

