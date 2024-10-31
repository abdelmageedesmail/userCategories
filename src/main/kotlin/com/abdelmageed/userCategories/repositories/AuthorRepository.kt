package com.abdelmageed.userCategories.repositories

import com.abdelmageed.userCategories.domain.entity.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<AuthorEntity, String>