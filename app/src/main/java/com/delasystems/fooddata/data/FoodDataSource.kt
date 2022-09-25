package com.delasystems.fooddata.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.delasystems.fooddata.domain.Food
import javax.inject.Inject

class FoodDataSource @Inject constructor(
    private val repository: FoodDataRepository
) : PagingSource<Int, Food>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Food> {
        Log.v("RHAW", "FoodDataSource::load()")
        try {
            val nextPage = params.key ?: 1
            repository.setPageNumber(nextPage)
            val foods = repository.getFoods()
            return LoadResult.Page(
                data = foods,
                prevKey = if(nextPage == 1) null else nextPage - 1,
                nextKey = if(repository.remoteCurrentPage >= repository.remoteTotalPages) null else  nextPage + 1
            )
        } catch(ex: Exception) {
            Log.v("RHAW", "FoodDataSource::load() -- Exception -- ${ex.toString()}")
            return LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Food>): Int? {
        Log.v("RHAW", "FoodDataSource::getRefreshKey() - returning null")
        return null
    }

}