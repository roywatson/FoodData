package com.delasystems.fooddata.data

import android.util.Log
import com.delasystems.fooddata.BuildConfig
import com.delasystems.fooddata.FoodDataPrefs
import com.delasystems.fooddata.data.local.database.FoodDataDatabase
import com.delasystems.fooddata.data.local.database.SearchHistoryEntity
import com.delasystems.fooddata.data.local.preferences.FoodDataPreferencesSource
import com.delasystems.fooddata.data.remote.FoodDataApiService
import com.delasystems.fooddata.data.remote.FoodNutrients
import com.delasystems.fooddata.data.remote.RemoteFood
import com.delasystems.fooddata.data.remote.RemoteFoodDataSearchTerms
import com.delasystems.fooddata.domain.Food
import com.delasystems.fooddata.domain.Nutrient
import com.delasystems.fooddata.domain.SearchParams
import com.delasystems.fooddata.presentation.foodlist.FoodDataSearchTerms
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodDataRepository @Inject constructor(
    private val restApiInterface: FoodDataApiService,
    private val historyDatabase: FoodDataDatabase,
    private val preferences: FoodDataPreferencesSource
) {

    var remoteTotalHits: Long = -1
        get() = field
    var remoteTotalPages: Long = -1
        get() = field
    var remoteCurrentPage: Long = -1
        get() = field

    private var remoteTerms: RemoteFoodDataSearchTerms? = null
    fun setSearchTerms(terms: FoodDataSearchTerms?) {
        Log.v("RHAW", "FoodDataRepository:: setSearchTerms() - ${terms.toString()}")
        if (terms == null) {
            remoteTerms = null
        } else {
            remoteTerms = RemoteFoodDataSearchTerms(
                description = terms.description,
                page = terms.page,
                count = terms.count,
                sortBy = terms.sortBy,
                sortOrder = terms.sortOrder,
                brandOwner = terms.brandOwner,
                dataType = terms.dataType
            )
        }
    }

    fun setPageNumber(page: Int) {
        Log.v("RHAW", "FoodDataRepository::setPageNumber($page)")
        remoteTerms?.page = page
    }

    suspend fun getFoods(): List<Food> {
        remoteTerms?.let {
            val remotefoods = restApiInterface.getFoodList(it, BuildConfig.FOODDATA_KEY)
            remoteTotalHits = remotefoods?.totalHits?.toLong() ?: -1
            remoteTotalPages = remotefoods?.totalPages?.toLong() ?: -1
            remoteCurrentPage = remotefoods?.currentPage?.toLong() ?: -1
            val foods = remotefoods?.foods?.map { remotefood ->
                Food.fromRemoteFood(
                    remotefood,
                    remoteTotalHits
                )
            }
            Log.v("RHAW", "FoodDataRepository::getFoods() -- foods=${foods?.size}")
            return foods?.toList() ?: listOf()
        }
        return listOf()
    }


    suspend fun saveSearchTerms(params: SearchParams) {
        historyDatabase.historyDao().insertFoodSearchToHistory(SearchHistoryEntity.fromSearchParams(params))
    }


    suspend fun setMostRecentSearch(params: SearchParams) {
        preferences.setSearchStrings(params.anyWords, params.allWords, params.noneWords)
    }

    val preferencesFlow: Flow<FoodDataPrefs> = preferences.preferencesFlow

    suspend fun clearMostRecentSearch() {
        setMostRecentSearch(SearchParams("","",""))
    }

    fun SearchHistoryEntity.Companion.fromSearchParams(params: SearchParams): SearchHistoryEntity {
        return SearchHistoryEntity(
            anyWords = params.anyWords,
            allWords = params.allWords,
            noneWords = params.noneWords,
            mostRecentUse = System.currentTimeMillis(),
            isFavorite = 0
        )
    }

    fun SearchParams.Companion.fromSearchHistoryEntity(entity: SearchHistoryEntity): SearchParams {
        return SearchParams(
            anyWords = entity.anyWords,
            allWords = entity.allWords,
            noneWords = entity.noneWords
        )
    }

    fun Food.Companion.fromRemoteFood(rfood: RemoteFood, totalHits: Long = -1): Food {
        return Food(
            fdcId = rfood.fdcId,
            totalHits = totalHits,
            description = rfood.description,
            lowercaseDescription = rfood.lowercaseDescription,
            dataType = rfood.dataType,
            gtinUpc = rfood.gtinUpc,
            publishedDate = rfood.publishedDate,
            brandOwner = rfood.brandOwner,
            brandName = rfood.brandName,
            ingredients = rfood.ingredients,
            marketCountry = rfood.marketCountry,
            foodCategory = rfood.foodCategory,
            modifiedDate = rfood.modifiedDate,
            dataSource = rfood.dataSource,
            packageWeight = rfood.packageWeight,
            servingSizeUnit = rfood.servingSizeUnit,
            servingSize = rfood.servingSize,
            allHighlightFields = rfood.allHighlightFields,
            score = rfood.score,
            foodNutrients = rfood.foodNutrients?.map { Nutrient.fromRemoteNutrient(it) }
        )
    }

    fun Nutrient.Companion.fromRemoteNutrient(rnut: FoodNutrients): Nutrient {
        return Nutrient(
            nutrientId = rnut.nutrientId,
            nutrientName = rnut.nutrientName,
            nutrientNumber = rnut.nutrientNumber,
            unitName = rnut.unitName,
            derivationCode = rnut.derivationCode,
            derivationDescription = rnut.derivationDescription,
            derivationId = rnut.derivationId,
            value = rnut.value,
            foodNutrientSourceId = rnut.foodNutrientSourceId,
            foodNutrientSourceCode = rnut.foodNutrientSourceCode,
            foodNutrientSourceDescription = rnut.foodNutrientSourceDescription,
            rank = rnut.rank,
            indentLevel = rnut.indentLevel,
            foodNutrientId = rnut.foodNutrientId,
            percentDailyValue = rnut.percentDailyValue
        )
    }
}