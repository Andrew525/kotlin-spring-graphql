package com.example.kotlindemo.web

import com.example.kotlindemo.model.Author
import com.example.kotlindemo.model.Book
import com.example.kotlindemo.repository.AuthorRepository
import com.example.kotlindemo.repository.BookRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.BatchMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono

@Controller
class BookQuery(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository) {


    @QueryMapping
    fun allBooks(@Argument limit: Int): List<Book> {
        return bookRepository.findAll(limit)
    }

//    @SchemaMapping(field = "author", typeName = "Book")
//    fun author(book: Book): Author {
//        return authorRepository.findById(book.authorId)
//    }

    @BatchMapping(field = "author", typeName = "Book")
    fun author(books: List<Book>): Mono<Map<Book, Author>> {
        val ids = books.map { it.authorId }.toSet()
        val authors = authorRepository.findAlByIdsAsMap(ids)
        val resultMapping = books.associateWith { authors.getValue(it.authorId) }
        return Mono.just(resultMapping)

    }
}
