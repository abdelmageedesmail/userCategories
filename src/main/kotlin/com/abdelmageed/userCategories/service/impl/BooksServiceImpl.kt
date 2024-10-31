package com.abdelmageed.userCategories.service.impl

import com.abdelmageed.userCategories.domain.entity.AuthorEntity
import com.abdelmageed.userCategories.domain.entity.BookEntity
import com.abdelmageed.userCategories.repositories.AuthorRepository
import com.abdelmageed.userCategories.repositories.BooksRepository
import com.abdelmageed.userCategories.service.AuthorService
import com.abdelmageed.userCategories.service.BooksService
import org.apache.coyote.BadRequestException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Service
class BooksServiceImpl(private val booksRepository: BooksRepository, private val authorRepository: AuthorRepository) :
    BooksService {

    override fun createBook(
        path: String,
        title: String,
        description: String,
        fileMultipart: MultipartFile,
        authorId: Int
    ): String? {
        try {
            val author = authorRepository.findByIdOrNull(authorId.toString())
            if (author != null) {
                val fileName = fileMultipart.originalFilename ?: throw IllegalArgumentException("File name is missing.")
                val cleanFileName = fileName.substringAfterLast('/', fileName)
                    .substringAfterLast('\\', fileName)

                val uniqueFileName = "${System.currentTimeMillis()}_$cleanFileName"

                val filePath = path + File.separator + uniqueFileName
                val imageFile = File(path)

                if (!imageFile.exists()) {
                    imageFile.mkdir()
                }

                Files.copy(fileMultipart.inputStream, Paths.get(filePath))

                val book = BookEntity(title = title, description = description, image = uniqueFileName, author = author)
                booksRepository.save(book)
                return "Book Created successfully"
            } else {
                return "No User found"
            }
        } catch (e: Exception) {
            return e.message.toString()
        }
    }

    override fun getAuthorBooks(authorId: Int): List<BookEntity>? {
        val author = authorRepository.findById(authorId.toString()).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with id $authorId")
        }
        return author?.books
    }

    override fun getSingleBook(bookId: Int): BookEntity? {
        return booksRepository.findByIdOrNull(bookId.toString())
    }
}