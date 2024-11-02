package com.abdelmageed.userCategories.service.impl

import com.abdelmageed.userCategories.domain.entity.AuthorEntity
import com.abdelmageed.userCategories.domain.entity.AuthorUpdateRequest
import com.abdelmageed.userCategories.repositories.AuthorRepository
import com.abdelmageed.userCategories.service.AuthorService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@Service
class AuthorServiceImpl(
    private val authorRepository: AuthorRepository
) :
    AuthorService {


    override fun createUser(
        path: String,
        name: String,
        age: Int,
        description: String,
        fileMultipart: MultipartFile
    ): AuthorEntity {
        return try {

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

            val authorEntity =
                AuthorEntity(
                    name = name,
                    age = age,
                    description = description,
                    image = uniqueFileName
                )
            return authorRepository.save(authorEntity)
        } catch (exception: IOException) {
            throw RuntimeException("Failed to read stored files", exception)
        }


    }

    override fun getAllAuthors(): List<AuthorEntity> = authorRepository.findAll()
    override fun getAuthorById(id: Int): AuthorEntity? = authorRepository.findByIdOrNull(id.toString())
    override fun deleteAuthor(id: Int) {
        authorRepository.deleteById(id.toString())
    }

    override fun partialUpdate(
        id: Int,
        path: String,
        name: String?,
        age: Int?,
        description: String?,
        fileMultipart: MultipartFile?
    ): AuthorEntity {
        val author = authorRepository.findById(id.toString()).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with id $id")
        }
        var fileName: String? = null
        var uniqueFileName: String? = null
        author?.let {
            fileName = fileMultipart?.originalFilename
            val cleanFileName = fileName?.substringAfterLast('/', fileName ?: "")
                ?.substringAfterLast('\\', fileName ?: "")

            uniqueFileName = if (fileName != null) {
                "${System.currentTimeMillis()}_$cleanFileName"
            } else {
                author.image
            }


            val filePath = path + File.separator + uniqueFileName
            val imageFile = File(path)

            if (!imageFile.exists()) {
                imageFile.mkdir()
            }

            fileMultipart?.let { Files.copy(it.inputStream, Paths.get(filePath)) }
        }

        val updateRequest = author.copy(
            id = author.id,
            name = name ?: author.name,
            description = description ?: author.description,
            image = uniqueFileName
        )
        return authorRepository.save(updateRequest)
    }
}