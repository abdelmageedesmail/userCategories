package com.abdelmageed.userCategories.domain.dto

data class AuthorUpdateRequestDto(
    val id: Int? = null,
    val name: String?,
    val age: Int?,
    val description: String?,
    val image: String?
)
