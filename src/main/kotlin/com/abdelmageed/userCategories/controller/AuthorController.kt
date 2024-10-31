package com.abdelmageed.userCategories.controller

import com.abdelmageed.userCategories.domain.dto.*
import com.abdelmageed.userCategories.service.AuthorService
import com.abdelmageed.userCategories.toAuthorDto
import com.abdelmageed.userCategories.toAuthorEntity
import com.abdelmageed.userCategories.toAuthorResponse
import com.abdelmageed.userCategories.toAuthorUpdateRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/v1/authors")
class AuthorController(private val authorService: AuthorService) {

    @Value("\${project.image}")
    val path: String? = null

    @PostMapping
    fun createAuthor(
        @RequestParam("name") name: String,
        @RequestParam("age") age: Int,
        @RequestParam("description") description: String,
        @RequestParam("image") imageFile: MultipartFile,
    ): BaseResponse<AuthorsDto> {
        try {
            val createUser =
                authorService.createUser(
                    path ?: "",
                    name = name,
                    age = age,
                    description = description,
                    imageFile = imageFile
                )
            return BaseResponse(
                HttpStatus.CREATED.value(),
                data = createUser.toAuthorDto()
            )

        } catch (e: Exception) {
            return BaseResponse(
                HttpStatus.BAD_REQUEST.value(),
                message = "Error in saving data"
            )
        }
    }


    @GetMapping
    fun getAllAuthors(): WrapperListResponse<AuthorResponse> {
        return WrapperListResponse(HttpStatus.OK.value(), authorService.getAllAuthors().map {
            it.toAuthorResponse().image = authorService.getImageUrl(it)
            it.toAuthorResponse()
        })
    }

    @GetMapping(path = ["/{id}"])
    fun getAuthorById(@PathVariable("id") id: Int): BaseResponse<AuthorResponse?>? {
        val author = authorService.getAuthorById(id)?.toAuthorDto()
        return author?.let {
            val authorResponse = AuthorResponse(
                id = it.id ?: 0,
                name = it.name,
                age = it.age,
                description = it.description,
                image = authorService.getImageUrl(it.toAuthorEntity())
            )
            BaseResponse(HttpStatus.OK.value(), authorResponse)
        } ?: run {
            BaseResponse(HttpStatus.BAD_REQUEST.value(), message = "User not found")
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteAuthor(@PathVariable("id") id: Int): BaseResponse<Any?>? {
        authorService.deleteAuthor(id)
        return BaseResponse(HttpStatus.NO_CONTENT.value())
    }

    @PatchMapping(path = ["/{id}"])
    fun updateAuthor(
        @PathVariable("id") id: Int,
        @RequestParam name: String?,
        @RequestParam age: Int?,
        @RequestParam description: String?,
        @RequestParam fileMultipart: MultipartFile?
    ): BaseResponse<AuthorResponse?>? {
        val partialUpdate = authorService.partialUpdate(
            id,
            path,
            name,
            age,
            description,
            fileMultipart
        )
        return BaseResponse(200, data = partialUpdate.toAuthorResponse(), "User updated successfully")
    }
}