package com.delasystems.fooddata.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =
        [
            SearchHistoryEntity::class,
            FoodHistoryEntity::class,
            FoodNutrientHistoryEntity::class,
        ],
    version = 1,
    exportSchema = true
)
abstract class FoodDataDatabase: RoomDatabase() {
    abstract fun historyDao(): FoodDataDao
}