package com.abdelmageed.userCategories.domain.dto

import org.springframework.context.annotation.Description

data class BooksResponse(
    val id: Int?,
    val title: String,
    val description: String,
    var image: String
)