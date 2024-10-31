package com.abdelmageed.userCategories.domain.dto

import com.abdelmageed.userCategories.domain.entity.AuthorEntity
import jakarta.persistence.*

data class BooksDto(
    val isbn: Int? = null,
    val title: String,
    val description: String,
    val image: String,
    val author: AuthorEntity
)
