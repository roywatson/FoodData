package com.delasystems.fooddata.data.remote

import retrofit2.http.*

interface FoodDataApiService {

    @Headers("accept: application/json", "Content-Type: application/json")
    @POST("fdc/v1/foods/search?")
    suspend fun getFoodList(@Body searchTerms: RemoteFoodDataSearchTerms, @Query("api_key") key: String): RemoteFoodList?
}