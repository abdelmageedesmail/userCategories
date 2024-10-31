package com.abdelmageed.userCategories.repositories

import com.abdelmageed.userCategories.domain.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BooksRepository : JpaRepository<BookEntity, String>