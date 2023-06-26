package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Book
import org.springframework.stereotype.Repository

@Repository
class BookRepository {

    fun findAll(limit: Int): List<Book> =
        BOOKS.take(limit)
            .also { println("Get all request with limit $limit") }

    companion object {
        val BOOKS =  listOf(
            Book(1, "Patterns of Enterprise Application Architecture", 1),
            Book(2, "Reactive Spring", 2),
            Book(3, "Domain Specific Languages", 1),
            Book(4, "Refactoring", 1)
        )
    }
}
