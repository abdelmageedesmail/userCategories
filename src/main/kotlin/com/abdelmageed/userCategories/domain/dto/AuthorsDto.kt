package com.abdelmageed.userCategories.domain.dto

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class AuthorsDto(
    val id: Int? = null,
    val name: String,
    val age: Int,
    val description: String,
    val image: String?

)