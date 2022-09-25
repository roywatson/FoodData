package com.delasystems.fooddata.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.delasystems.fooddata.data.FoodDataRepository
import com.delasystems.fooddata.data.FoodDataSource
import com.delasystems.fooddata.domain.Food
import com.delasystems.fooddata.domain.SearchParams
import com.delasystems.fooddata.navigation.NavItem
import com.delasystems.fooddata.presentation.fooddetails.FoodDetailsScreenState
import com.delasystems.fooddata.presentation.foodlist.FoodDataSearchTerms
import com.delasystems.fooddata.presentation.foodlist.FoodsListScreenState
import com.delasystems.fooddata.presentation.search.FoodSearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel  @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: FoodDataRepository
): ViewModel() {

    val items = listOf(NavItem.SearchScreen, NavItem.HistoryScreen, NavItem.FavoritesScreen)

    init {
        observePreferences()
    }

    private val _stateFoodSearch = MutableStateFlow(
        FoodSearchScreenState(
            allWords = "",
            anyWords = "",
            noneWords = ""
        )
    )
    val stateFoodSearch: StateFlow<FoodSearchScreenState>
        get() = _stateFoodSearch

    private val _stateFoodsList = mutableStateOf(
        FoodsListScreenState(
            foodsFlow = emptyFlow(),
            isLoading = true)
    )
    val stateFoodsList: State<FoodsListScreenState>
        get() = _stateFoodsList


    private val _stateFoodDetails = mutableStateOf(
        FoodDetailsScreenState(
            food = null,
            isLoading = true)
    )
    val stateFoodDetails: State<FoodDetailsScreenState>
        get() = _stateFoodDetails


    fun  searchFlow(terms: FoodDataSearchTerms?): Flow<PagingData<Food>> {
        repository.setSearchTerms(terms)
        val pager = Pager(PagingConfig(pageSize = 25)) { FoodDataSource(repository) }
        return pager.flow
    }

    fun searchAdvanced(anyWords: String, allWords: String, noneWords: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val anys = anyWords.split(" ", ",").filter { it.isNotBlank() }
            val alls = allWords.split(" ", ",").filter { it.isNotBlank() }.map { "+$it" }
            val nones = noneWords.split(" ", ",").filter { it.isNotBlank() }.map { "-$it" }

            var query = ""
            if (anys.isNotEmpty()) {
                query += anys.joinToString(" ") + " "
            }
            if (alls.isNotEmpty()) {
                query += alls.joinToString(" ") + " "
            }
            if (nones.isNotEmpty()) {
                query += nones.joinToString(" ")
            }
            search(FoodDataSearchTerms(description = query))
            repository.saveSearchTerms(SearchParams(anyWords, allWords, noneWords))
            repository.setMostRecentSearch(SearchParams(anyWords, allWords, noneWords))
        }
    }

    fun search(terms: FoodDataSearchTerms) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setSearchTerms(terms)
            val items = searchFlow(terms)
            _stateFoodsList.value = _stateFoodsList.value.copy(foodsFlow = items, isLoading = false)
        }
    }


    fun setCurrentFood(food: Food) {
        _stateFoodDetails.value = _stateFoodDetails.value.copy(food = food)
    }


    fun observePreferences() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.preferencesFlow
                .collect { prefs ->
                    _stateFoodSearch.value = _stateFoodSearch.value.copy(
                        anyWords = prefs.searchAnywords,
                        allWords = prefs.searchAllwords,
                        noneWords = prefs.searchNonewords
                    )
                }
        }
    }

    fun clearMostRecentSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearMostRecentSearch()
        }
    }

}