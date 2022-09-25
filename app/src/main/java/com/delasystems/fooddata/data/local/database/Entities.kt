package com.delasystems.fooddata.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "tbl_search_history",
    primaryKeys = ["use_ts"]
)
data class SearchHistoryEntity(
    @ColumnInfo(name = "use_ts", defaultValue = "-1")
    val mostRecentUse: Long,
    @ColumnInfo(name = "any_words", defaultValue = "")
    val anyWords: String,
    @ColumnInfo(name = "all_words", defaultValue = "")
    val allWords: String,
    @ColumnInfo(name = "none_words", defaultValue = "")
    val noneWords: String,
    @ColumnInfo(name = "is_favorite", defaultValue = "0")
    val isFavorite: Int
) {
    companion object {}
}

@Entity(
    tableName = "tbl_food_history",
    primaryKeys = ["fdc_id"]
)
data class FoodHistoryEntity(
    @ColumnInfo(name = "fdc_id", defaultValue = "-1")
    val fdcId: Int,
    @ColumnInfo(name = "description", defaultValue = "")
    val description: String? = null,
    @ColumnInfo(name = "lower_desc", defaultValue = "")
    val lowercaseDescription: String? = null,
    @ColumnInfo(name = "data_type", defaultValue = "")
    val dataType: String? = null,
    @ColumnInfo(name = "gtin_upc", defaultValue = "")
    val gtinUpc: String? = null,
    @ColumnInfo(name = "pub_date", defaultValue = "")
    val publishedDate: String? = null,
    @ColumnInfo(name = "brand_owner", defaultValue = "")
    val brandOwner: String? = null,
    @ColumnInfo(name = "brand_name", defaultValue = "")
    val brandName: String? = null,
    @ColumnInfo(name = "ingredients", defaultValue = "")
    val ingredients: String? = null,
    @ColumnInfo(name = "mkt_country", defaultValue = "")
    val marketCountry: String? = null,
    @ColumnInfo(name = "food_catagory", defaultValue = "")
    val foodCategory: String? = null,
    @ColumnInfo(name = "moded_date", defaultValue = "")
    val modifiedDate: String? = null,
    @ColumnInfo(name = "data_source", defaultValue = "")
    val dataSource: String? = null,
    @ColumnInfo(name = "pkg_weight", defaultValue = "-1")
    val packageWeight: String? = null,
    @ColumnInfo(name = "serving_size_unit", defaultValue = "")
    val servingSizeUnit: String? = null,
    @ColumnInfo(name = "serving_size", defaultValue = "")
    val servingSize: Double? = null,
    @ColumnInfo(name = "all_hilite_flds", defaultValue = "")
    val allHighlightFields: String? = null,
    @ColumnInfo(name = "score", defaultValue = "")
    val score: Double? = null,
    @ColumnInfo(name = "is_favorite", defaultValue = "0")
    val isFavorite: Int
)

@Entity(
    tableName = "tbl_food_nutrient_history",
    primaryKeys = ["fdc_id"]
)
data class FoodNutrientHistoryEntity(
    @ColumnInfo(name = "fdc_id", defaultValue = "-1")
    val fdcId: Int,
    @ColumnInfo(name = "nutrient_id", defaultValue = "-1")
    val nutrientId: Int?,
    @ColumnInfo(name = "nutrient_name", defaultValue = "")
    val nutrientName: String?,
    @ColumnInfo(name = "nutrient_number", defaultValue = "")
    val nutrientNumber: String?,
    @ColumnInfo(name = "unit_name", defaultValue = "")
    val unitName: String?,
    @ColumnInfo(name = "derivation_code", defaultValue = "")
    val derivationCode: String?,
    @ColumnInfo(name = "derivation_desc", defaultValue = "")
    val derivationDescription: String?,
    @ColumnInfo(name = "derivation_id", defaultValue = "-1")
    val derivationId: Int?,
    @ColumnInfo(name = "value", defaultValue = "")
    val value: Double?,
    @ColumnInfo(name = "food_nutrient_src_id", defaultValue = "")
    val foodNutrientSourceId: Int?,
    @ColumnInfo(name = "food_nutrient_src_code", defaultValue = "")
    val foodNutrientSourceCode: String?,
    @ColumnInfo(name = "food_nutrient_src_desc", defaultValue = "")
    val foodNutrientSourceDescription: String?,
    @ColumnInfo(name = "rank", defaultValue = "")
    val rank: Int?,
    @ColumnInfo(name = "indent_lvl", defaultValue = "")
    val indentLevel: Int?,
    @ColumnInfo(name = "food_nutrient_id", defaultValue = "")
    val foodNutrientId: Int?,
    @ColumnInfo(name = "percent_daily_value", defaultValue = "")
    val percentDailyValue: Double?
)


