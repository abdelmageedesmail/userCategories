package com.abdelmageed.userCategories.domain.dto

data class AuthorResponse(
    val id: Int?,
    val name: String,
    val age: Int,
    val description: String,
    var image: String
)