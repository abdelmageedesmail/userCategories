package com.abdelmageed.userCategories.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "authors")
data class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
//    @SequenceGenerator(name = "authors_seq_gen", sequenceName = "authors_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, unique = true)
    val id: Int? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "age")
    val age: Int,
    @Column(name = "description")
    val description: String,
    @Column(name = "image")
    var image: String,
    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val books: List<BookEntity> = emptyList()
)
