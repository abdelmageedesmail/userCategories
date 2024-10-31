package com.abdelmageed.userCategories.controller

import com.abdelmageed.userCategories.domain.dto.BaseResponse
import com.abdelmageed.userCategories.domain.dto.BooksResponse
import com.abdelmageed.userCategories.domain.dto.WrapperListResponse
import com.abdelmageed.userCategories.service.BooksService
import com.abdelmageed.userCategories.toBookResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/books")
class BooksController(private val booksService: BooksService) {

    @Value("\${project.image}")
    val path: String? = null

    @PostMapping
    fun createBook(
        @RequestParam("title") title: String,
        @RequestParam("description") description: String,
        @RequestParam("image") imageFile: MultipartFile,
        @RequestParam("author_id") id: Int
    ): BaseResponse<String> {
        path?.let {
            return BaseResponse(
                HttpStatus.CREATED.value(),
                message = booksService.createBook(it, title, description, imageFile, id)
            )
        } ?: run { return BaseResponse(HttpStatus.BAD_GATEWAY.value(), message = "No Author Found") }
    }

    @GetMapping(path = ["/{author_id}"])
    fun getAllBooks(
        @PathVariable(name = "author_id") authorId: Int
    ): WrapperListResponse<BooksResponse>? {
        return if (!booksService.getAuthorBooks(authorId).isNullOrEmpty()) {
            WrapperListResponse(HttpStatus.OK.value(), data = booksService.getAuthorBooks(authorId)?.map {
                it.toBookResponse()
            })
        } else {
            WrapperListResponse(HttpStatus.NOT_FOUND.value(), message = "No Books for this author")
        }
    }

    @GetMapping(path = ["/book/{book_id}"])
    fun getSingleBook(@PathVariable(name = "book_id") bookId: Int): BaseResponse<BooksResponse>? {
        return if (booksService.getSingleBook(bookId) != null) {
            BaseResponse(HttpStatus.OK.value(), data = booksService.getSingleBook(bookId)?.toBookResponse())
        } else {
            BaseResponse(
                HttpStatus.NOT_FOUND.value(),
                data = booksService.getSingleBook(bookId)?.toBookResponse(),
                message = "No book"
            )
        }
    }

}