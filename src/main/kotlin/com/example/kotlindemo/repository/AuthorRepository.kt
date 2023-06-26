package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Author
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.ArrayList

@Repository
class AuthorRepository {

    fun findById(authorId: Int): Author {
        println("Find By author id: $authorId")
        return AUTHOR[authorId] ?: throw RuntimeException("Not found with id $authorId")
    }

    fun findAlByIdsAsMap(ids: Set<Int>): Map<Int, Author> {
        return Companion.AUTHOR.filterKeys{ it in ids }
            .also { println("Find All Authors by Ids: $ids") }
    }
    private companion object {
        val AUTHOR = listOf(
            Author(1, "Martin", "Fowler"),
            Author(2, "Josh", "Long"),
        ).associateBy { it.id }
    }
}
