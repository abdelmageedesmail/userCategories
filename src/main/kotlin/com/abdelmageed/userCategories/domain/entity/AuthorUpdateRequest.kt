package com.abdelmageed.userCategories.domain.entity

data class AuthorUpdateRequest(
    val id: Int? = null,
    val name: String?,
    val age: Int?,
    val description: String?,
    val image: String?
)
