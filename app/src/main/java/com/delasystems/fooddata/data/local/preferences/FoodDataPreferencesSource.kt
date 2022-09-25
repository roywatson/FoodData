package com.delasystems.fooddata.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.delasystems.fooddata.FoodDataPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class FoodDataPreferencesSource @Inject constructor(val context: Context) {

    private val Context.settingsDataStore: DataStore<FoodDataPrefs> by dataStore(
        fileName = "fooddata_prefences.pb",
        serializer = MyPrefsSerializer
    )

    val preferencesFlow: Flow<FoodDataPrefs> = context.settingsDataStore.data
        .catch {
            emit(FoodDataPrefs.getDefaultInstance())
        }

    suspend fun setSearchStrings(anyWords: String, allWords: String, noneWords: String) {
        context.settingsDataStore.updateData { currentValues ->
            currentValues
                .toBuilder()
                .setSearchAnywords(anyWords)
                .setSearchAllwords(allWords)
                .setSearchNonewords(noneWords)
                .build()
        }
    }

}