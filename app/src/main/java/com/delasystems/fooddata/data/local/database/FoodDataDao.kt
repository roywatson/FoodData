package com.delasystems.fooddata.data.local.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoodSearchToHistory(search: SearchHistoryEntity): Long

    @Query("SELECT * FROM tbl_search_history ORDER BY use_ts ASC LIMIT 1")
    fun getFoodSearchMostRecentHistory(): SearchHistoryEntity

    @Query("SELECT * FROM tbl_search_history ORDER BY use_ts ASC")
    fun getFoodSearchHistory(): Flow<SearchHistoryEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFoodHistory(food: FoodHistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFoodHistoryNutrients(nutrients: List<FoodNutrientHistoryEntity>)

    @Transaction
    fun insertFoodHistoryTransaction(
        food: FoodHistoryEntity,
        nutrients: List<FoodNutrientHistoryEntity>) {

        insertFoodHistory(food)
        insertFoodHistoryNutrients(nutrients)
    }
}