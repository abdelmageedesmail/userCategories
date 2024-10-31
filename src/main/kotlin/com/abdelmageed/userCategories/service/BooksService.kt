package com.abdelmageed.userCategories.service

import com.abdelmageed.userCategories.domain.entity.AuthorEntity
import com.abdelmageed.userCategories.domain.entity.BookEntity
import org.springframework.web.multipart.MultipartFile

interface BooksService {
    fun createBook(
        path: String,
        title: String,
        description: String,
        fileMultipart: MultipartFile,
        authorId: Int
    ): String?


    fun getAuthorBooks(authorId: Int): List<BookEntity>?

    fun getSingleBook(bookId: Int): BookEntity?

    fun getImageUrl(book: BookEntity): String {
        // Assuming your server is running on localhost:8080
        return "http://localhost:8080/images/${book.image}"
    }

}