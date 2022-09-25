package com.delasystems.fooddata.domain

data class SearchParams(
    val anyWords: String = "",
    val allWords: String = "",
    val noneWords: String = ""
) {
    companion object {}
}
