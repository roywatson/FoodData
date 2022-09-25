package com.delasystems.fooddata.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteFoodDataSearchTerms(
    @SerializedName("query") var description: String? = null,
    @SerializedName("pageNumber") var page: Int = 1,
    @SerializedName("pageSize") var count: Int = 25,
    @SerializedName("sortBy") var sortBy: String? = null,
    @SerializedName("sortOrder") var sortOrder: String? = null,
    @SerializedName("brandOwner") var brandOwner: String? = null,
    @SerializedName("dataType") var dataType: List<String>? = null
)
