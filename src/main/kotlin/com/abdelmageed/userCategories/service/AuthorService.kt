package com.abdelmageed.userCategories.service

import com.abdelmageed.userCategories.domain.dto.BaseResponse
import com.abdelmageed.userCategories.domain.entity.AuthorEntity
import com.abdelmageed.userCategories.domain.entity.AuthorUpdateRequest
import com.abdelmageed.userCategories.repositories.AuthorRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface AuthorService {
    fun createUser(path: String, name: String, age: Int, description: String, imageFile: MultipartFile): AuthorEntity
    fun getAllAuthors(): List<AuthorEntity>
    fun getAuthorById(id: Int): AuthorEntity?
    fun deleteAuthor(id: Int)
    fun partialUpdate(
        id: Int, path: String,
        name: String?,
        age: Int?,
        description: String?,
        fileMultipart: MultipartFile?
    ): AuthorEntity

    fun getImageUrl(user: AuthorEntity): String {
        // Assuming your server is running on localhost:8080
        return "http://localhost:8080/images/${user.image}"
    }
}