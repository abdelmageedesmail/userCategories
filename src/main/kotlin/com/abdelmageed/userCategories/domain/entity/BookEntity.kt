package com.abdelmageed.userCategories.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "books")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_isbn_seq")
    @Column(name = "isbn")
    val isbn: Int? = null,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String,
    @Column(name = "image")
    val image: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    val author: AuthorEntity
)
