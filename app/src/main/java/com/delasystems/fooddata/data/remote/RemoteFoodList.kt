package com.delasystems.fooddata.data.remote

data class RemoteFoodList(
    val totalHits: Int,
    val currentPage: Int,
    val totalPages: Int,
    val pageList: List<Int>,
    val foodSearchCriteria: FoodSearchCriteria?,
    val foods: List<RemoteFood>
)

data class FoodSearchCriteria(
    val query: String?,
    val generalSearchInput: String?,
    val pageNumber: Int?,
    val numberOfResultsPerPage: Int?,
    val pageSize: Int?,
    val requireAllWords: Boolean?
)

data class FoodNutrients(
    val nutrientId: Int?,
    val nutrientName: String?,
    val nutrientNumber: String?,
    val unitName: String?,
    val derivationCode: String?,
    val derivationDescription: String?,
    val derivationId: Int?,
    val value: Double?,
    val foodNutrientSourceId: Int?,
    val foodNutrientSourceCode: String?,
    val foodNutrientSourceDescription: String?,
    val rank: Int?,
    val indentLevel: Int?,
    val foodNutrientId: Int?,
    val percentDailyValue: Double?
)

data class RemoteFood(
    val fdcId: Int,
    val description: String?,
    val lowercaseDescription: String?,
    val dataType: String?,
    val gtinUpc: String?,
    val publishedDate: String?,
    val brandOwner: String?,
    val brandName: String?,
    val ingredients: String?,
    val marketCountry: String?,
    val foodCategory: String?,
    val modifiedDate: String?,
    val dataSource: String?,
    val packageWeight: String?,
    val servingSizeUnit: String?,
    val servingSize: Double?,
    val allHighlightFields: String?,
    val score: Double?,
    val foodNutrients: List<FoodNutrients>?
)
