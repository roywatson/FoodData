package com.delasystems.fooddata.domain

data class Food(
    val fdcId: Int = -1,
    val totalHits: Long = -1,
    val description: String? = null,
    val lowercaseDescription: String? = null,
    val dataType: String? = null,
    val gtinUpc: String? = null,
    val publishedDate: String? = null,
    val brandOwner: String? = null,
    val brandName: String? = null,
    val ingredients: String? = null,
    val marketCountry: String? = null,
    val foodCategory: String? = null,
    val modifiedDate: String? = null,
    val dataSource: String? = null,
    val packageWeight: String? = null,
    val servingSizeUnit: String? = null,
    val servingSize: Double? = null,
    val allHighlightFields: String? = null,
    val score: Double? = null,
    val foodNutrients: List<Nutrient>? = null
) {
    companion object {}
}

data class Nutrient(
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
) {
    companion object {}
}
