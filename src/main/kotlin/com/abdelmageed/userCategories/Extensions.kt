package com.abdelmageed.userCategories

import com.abdelmageed.userCategories.domain.dto.*
import com.abdelmageed.userCategories.domain.entity.AuthorEntity
import com.abdelmageed.userCategories.domain.entity.AuthorUpdateRequest
import com.abdelmageed.userCategories.domain.entity.BookEntity
import java.util.*

fun AuthorEntity.toAuthorDto() = AuthorsDto(
    id = id,
    name = name,
    age = age,
    description = description,
    image = image
)


fun AuthorsDto.toAuthorEntity() = AuthorEntity(
    id = id ?: 0,
    name = name ?: "",
    age = age ?: 0,
    description = description ?: "",
    image = image
)

fun AuthorEntity.toAuthorResponse() = AuthorResponse(
    id = id ?: 0,
    name = name,
    age = age,
    description = description,
    image = "http://localhost:8080/images/$image"
)

fun AuthorUpdateRequest.toAuthorUpdateRequestDto() = AuthorUpdateRequestDto(
    id = id,
    name = name,
    age = age,
    description = description,
    image = "http://localhost:8080/images/$image"
)

fun AuthorUpdateRequestDto.toAuthorUpdateRequest() = AuthorUpdateRequest(
    id = id,
    name = name,
    age = age,
    description = description,
    image = "http://localhost:8080/images/$image"
)

fun BookEntity.toBookDto() = BooksDto(
    isbn = isbn,
    title = title,
    description = description,
    image = image,
    author = author
)

fun BookEntity.toBookResponse() = BooksResponse(
    id = isbn,
    title = title,
    description = description,
    image = "http://localhost:8080/images/$image"
)